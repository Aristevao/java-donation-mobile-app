package com.fho.donation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.fho.donation.databinding.ActivityMainBinding;
import com.google.android.material.badge.BadgeDrawable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
        initBadge();
    }

    private void initNavigation() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        // Limpa o valor do "não lido" quando o usuário clicar sobre o ícone do menu Notificações.
        navController.addOnDestinationChangedListener((navController, destination, bundle) -> {
            if (destination.getId() == R.id.menu_notification) {
                BadgeDrawable badgeDrawable = binding.bottomNavigation.getBadge(R.id.menu_notification);
                if (badgeDrawable != null) {
                    badgeDrawable.setVisible(false);
                    badgeDrawable.clearNumber();
                }
            }
        });
    }

    private void initBadge() {
        BadgeDrawable badge = binding.bottomNavigation.getOrCreateBadge(R.id.menu_notification);
        badge.setVisible(true);
        badge.setNumber(5); // TODO:  Deixar valor de "notificações não lidas" de forma dinâmica.

        badge.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        badge.setBadgeTextColor(ContextCompat.getColor(this, R.color.white));
        badge.setVerticalOffset(15);
        badge.setHorizontalOffset(-15);
    }
}