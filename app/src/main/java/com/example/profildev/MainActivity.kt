package com.example.profildev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profildev.ui.theme.ProfilDevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(
    isAvailable: Boolean = true,
    modifier: Modifier = Modifier
) {
    ProfileCard(
        name = "Elsa Letellier",
        role = "Développeuse Logicielle",
        description = "Étudiante en 4ème année de Master Informatique, spécialité Architecture Logicielle, je " +
                "recherche une alternance pour mettre en pratique mes compétences en conception et " +
                "développement logiciel.",
        skill1 = "NodeJs + React",
        skill2 = "Java",
        skill3 = "Anglais",
        isAvailable = isAvailable,
        onContactClick = {
            // Le clic ne fait rien pour l'instant.
        },
        onAvailabilityClick = {
            // Le clic ne fait rien pour l'instant.
        },
        // Padding externe : le parent place la fiche dans l'écran.
        modifier = modifier.padding(24.dp)
    )
}

@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skill1: String,
    skill2: String,
    skill3: String,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        // Padding interne : espace le contenu de la fiche.
        Column(modifier = Modifier.padding(16.dp)) {
            ProfileHeader(
                name = name,
                role = role,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = description,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "Compétences :",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            SkillItem(skill = skill1, modifier = Modifier.padding(bottom = 2.dp))
            SkillItem(skill = skill2, modifier = Modifier.padding(bottom = 2.dp))
            SkillItem(skill = skill3, modifier = Modifier.padding(bottom = 12.dp))
            AvailabilityBanner(
                isAvailable = isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            ActionButton(
                text = "Contacter",
                onClick = onContactClick
            )
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            fontSize = 24.sp
        )
        Text(
            text = role,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "• $skill",
        modifier = modifier
    )
}

@Composable
fun AvailabilityBanner(
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (isAvailable) {
            "Disponible pour une alternance"
        } else {
            "Actuellement indisponible"
        },
        color = if (isAvailable) {
            Color(0xFF2E7D32)
        } else {
            Color(0xFFC62828)
        },
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = "Disponibilité, appuyer pour en savoir plus"
            }
            .background(
                color = if (isAvailable) {
                    Color(0xFFE8F5E9)
                } else {
                    Color(0xFFFFEBEE)
                },
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    )
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenAvailablePreview() {
    ProfilDevTheme {
        ProfileScreen(isAvailable = true)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenUnavailablePreview() {
    ProfilDevTheme {
        ProfileScreen(isAvailable = false)
    }
}
