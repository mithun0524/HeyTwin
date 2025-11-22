package com.heytwin.auth;

import com.heytwin.auth.dto.AuthResponse;
import com.heytwin.auth.dto.LoginRequest;
import com.heytwin.auth.dto.RefreshTokenRequest;
import com.heytwin.auth.dto.RegisterRequest;
import com.heytwin.config.JwtService;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.model.enums.RoleType;
import com.heytwin.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        userRepository.findByEmailIgnoreCase(request.getEmail())
                .ifPresent(user -> { throw new IllegalArgumentException("Email already registered"); });

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .gradeLevel(request.getGradeLevel())
                .role(RoleType.STUDENT)
                .build();
        userRepository.save(user);

        return generateAuthResponse(UserPrincipal.of(user));
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        return generateAuthResponse(UserPrincipal.of(user));
    }

    public AuthResponse refresh(RefreshTokenRequest request) {
        // For simplicity, reuse same JWT expiration; extend later.
        String username = jwtService.extractUsername(request.getRefreshToken());
        User user = userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));
        return generateAuthResponse(UserPrincipal.of(user));
    }

    private AuthResponse generateAuthResponse(UserPrincipal principal) {
        String access = jwtService.generateToken(principal, Map.of("type", "access"));
        String refresh = jwtService.generateToken(principal, Map.of("type", "refresh"));
        long expiresIn = 3600L;
        return AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .expiresIn(expiresIn)
                .user(AuthResponse.UserSummary.builder()
                        .id(principal.getId().toString())
                        .fullName(principal.getFullName())
                        .role(principal.getAuthorities().stream()
                                .findFirst()
                                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                                .orElse("STUDENT"))
                        .build())
                .build();
    }
}
