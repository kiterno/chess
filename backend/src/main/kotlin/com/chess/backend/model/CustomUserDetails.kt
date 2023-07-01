package com.chess.backend.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomerUserDetails(private val user: User) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {

        val simpleGrantedAuthority = SimpleGrantedAuthority(user.role)

        return listOf(simpleGrantedAuthority)
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return user.enabled
    }
}
