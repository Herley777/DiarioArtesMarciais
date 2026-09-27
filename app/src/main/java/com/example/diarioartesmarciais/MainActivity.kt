package com.example.diarioartesmarciais

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

private val Laranja = Color(0xFFFF9800)
private val LaranjaEscuro = Color(0xFFE65100)
private val Preto = Color(0xFF000000)
private val Branco = Color(0xFFFFFFFF)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiarioArtesMarciaisApp()
        }
    }
}

data class Treino(
    val id: String,
    val modalidade: String,
    val duracao: String,
    val observacoes: String
)

@Composable
fun DiarioArtesMarciaisApp() {

    var telaAtual by remember {
        mutableStateOf("inicio")
    }

    var treinoParaEditar by remember {
        mutableStateOf<Treino?>(null)
    }

    when (telaAtual) {

        "inicio" -> {

            TelaInicial(
                onNovoTreino = {
                    telaAtual = "novo_treino"
                },
                onMeusTreinos = {
                    telaAtual = "meus_treinos"
                },
                onPerfil = {
                    telaAtual = "perfil"
                }
            )
        }

        "novo_treino" -> {

            TelaNovoTreino(
                onVoltar = {
                    telaAtual = "inicio"
                }
            )
        }

        "meus_treinos" -> {

            TelaMeusTreinos(
                onVoltar = {
                    telaAtual = "inicio"
                },
                onEditar = { treino ->

                    treinoParaEditar = treino
                    telaAtual = "editar_treino"
                }
            )
        }

        "editar_treino" -> {

            treinoParaEditar?.let { treino ->

                TelaEditarTreino(
                    treino = treino,
                    onVoltar = {
                        treinoParaEditar = null
                        telaAtual = "meus_treinos"
                    }
                )
            }
        }

        "perfil" -> {

            TelaPerfil(
                onVoltar = {
                    telaAtual = "inicio"
                }
            )
        }
    }
}

@Composable
fun TelaInicial(
    onNovoTreino: () -> Unit,
    onMeusTreinos: () -> Unit,
    onPerfil: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.fundo_martial
            ),
            contentDescription = "Fundo de artes marciais",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.45f)
                )
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Diário de Artes Marciais",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Branco
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Registre e acompanhe seus treinos",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Branco
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onNovoTreino,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Laranja,
                    contentColor = Preto
                )
            ) {

                Text(
                    text = "Novo Treino",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = onMeusTreinos,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Laranja,
                    contentColor = Preto
                )
            ) {

                Text(
                    text = "Meus Treinos",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = onPerfil,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Laranja,
                    contentColor = Preto
                )
            ) {

                Text(
                    text = "Meu Perfil",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CampoTexto(
    valor: String,
    titulo: String,
    onValorAlterado: (String) -> Unit
) {

    OutlinedTextField(
        value = valor,
        onValueChange = onValorAlterado,
        label = {
            Text(
                text = titulo,
                color = Preto
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Preto,
            unfocusedTextColor = Preto,
            focusedLabelColor = Preto,
            unfocusedLabelColor = Preto,
            cursorColor = Preto,
            focusedBorderColor = LaranjaEscuro,
            unfocusedBorderColor = Preto
        )
    )
}

@Composable
fun BarraTitulo(
    titulo: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Laranja)
            .padding(20.dp)
    ) {

        Text(
            text = titulo,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Preto
        )
    }
}

@Composable
fun TelaNovoTreino(
    onVoltar: () -> Unit
) {

    var modalidade by remember {
        mutableStateOf("")
    }

    var duracao by remember {
        mutableStateOf("")
    }

    var observacoes by remember {
        mutableStateOf("")
    }

    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Branco)
    ) {

        BarraTitulo(
            titulo = "Novo Treino"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            CampoTexto(
                valor = modalidade,
                titulo = "Modalidade",
                onValorAlterado = {
                    modalidade = it
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            CampoTexto(
                valor = duracao,
                titulo = "Duração",
                onValorAlterado = {
                    duracao = it
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            CampoTexto(
                valor = observacoes,
                titulo = "Observações",
                onValorAlterado = {
                    observacoes = it
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = {

                    val treino = hashMapOf(
                        "modalidade" to modalidade,
                        "duracao" to duracao,
                        "observacoes" to observacoes
                    )

                    db.collection("treinos")
                        .add(treino)
                        .addOnSuccessListener {

                            Toast.makeText(
                                context,
                                "Treino salvo com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()

                            onVoltar()
                        }
                        .addOnFailureListener { erro ->

                            Toast.makeText(
                                context,
                                "Erro: ${erro.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Laranja,
                    contentColor = Preto
                )
            ) {

                Text(
                    text = "Salvar Treino",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = onVoltar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Preto,
                    contentColor = Branco
                )
            ) {

                Text(
                    text = "Voltar",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TelaMeusTreinos(
    onVoltar: () -> Unit,
    onEditar: (Treino) -> Unit
) {

    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var treinos by remember {
        mutableStateOf<List<Treino>>(emptyList())
    }

    var carregando by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        db.collection("treinos")
            .get()
            .addOnSuccessListener { resultado ->

                val lista = mutableListOf<Treino>()

                for (documento in resultado.documents) {

                    val treino = Treino(
                        id = documento.id,
                        modalidade = documento.getString("modalidade") ?: "",
                        duracao = documento.getString("duracao") ?: "",
                        observacoes = documento.getString("observacoes") ?: ""
                    )

                    lista.add(treino)
                }

                treinos = lista
                carregando = false
            }
            .addOnFailureListener {

                carregando = false

                Toast.makeText(
                    context,
                    "Erro ao carregar treinos.",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Branco)
    ) {

        BarraTitulo(
            titulo = "Meus Treinos"
        )

        if (carregando) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "Carregando...",
                    color = Preto,
                    modifier = Modifier.padding(24.dp)
                )
            }

        } else if (treinos.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "Nenhum treino encontrado.",
                    color = Preto,
                    modifier = Modifier.padding(24.dp)
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(treinos) { treino ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Laranja
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "🥋 ${treino.modalidade}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Preto
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "Duração: ${treino.duracao}",
                                fontSize = 16.sp,
                                color = Preto
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "Observações: ${treino.observacoes}",
                                fontSize = 16.sp,
                                color = Preto
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Button(
                                onClick = {
                                    onEditar(treino)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Preto,
                                    contentColor = Branco
                                )
                            ) {

                                Text(
                                    text = "Editar",
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Button(
                                onClick = {

                                    db.collection("treinos")
                                        .document(treino.id)
                                        .delete()
                                        .addOnSuccessListener {

                                            treinos = treinos.filter {
                                                it.id != treino.id
                                            }

                                            Toast.makeText(
                                                context,
                                                "Treino excluído com sucesso!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        .addOnFailureListener { erro ->

                                            Toast.makeText(
                                                context,
                                                "Erro ao excluir: ${erro.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Preto,
                                    contentColor = Branco
                                )
                            ) {

                                Text(
                                    text = "Excluir",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = onVoltar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Preto,
                contentColor = Branco
            )
        ) {

            Text(
                text = "Voltar",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TelaEditarTreino(
    treino: Treino,
    onVoltar: () -> Unit
) {

    var modalidade by remember {
        mutableStateOf(treino.modalidade)
    }

    var duracao by remember {
        mutableStateOf(treino.duracao)
    }

    var observacoes by remember {
        mutableStateOf(treino.observacoes)
    }

    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Branco)
    ) {

        BarraTitulo(
            titulo = "Editar Treino"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            CampoTexto(
                valor = modalidade,
                titulo = "Modalidade",
                onValorAlterado = {
                    modalidade = it
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            CampoTexto(
                valor = duracao,
                titulo = "Duração",
                onValorAlterado = {
                    duracao = it
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            CampoTexto(
                valor = observacoes,
                titulo = "Observações",
                onValorAlterado = {
                    observacoes = it
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = {

                    val dadosAtualizados: Map<String, Any> = mapOf(
                        "modalidade" to modalidade,
                        "duracao" to duracao,
                        "observacoes" to observacoes
                    )

                    db.collection("treinos")
                        .document(treino.id)
                        .update(dadosAtualizados)
                        .addOnSuccessListener {

                            Toast.makeText(
                                context,
                                "Treino atualizado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()

                            onVoltar()
                        }
                        .addOnFailureListener { erro ->

                            Toast.makeText(
                                context,
                                "Erro ao atualizar: ${erro.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Laranja,
                    contentColor = Preto
                )
            ) {

                Text(
                    text = "Salvar Alterações",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = onVoltar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Preto,
                    contentColor = Branco
                )
            ) {

                Text(
                    text = "Cancelar",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TelaPerfil(
    onVoltar: () -> Unit
) {

    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    var nome by remember {
        mutableStateOf("")
    }

    var modalidade by remember {
        mutableStateOf("")
    }

    var objetivo by remember {
        mutableStateOf("")
    }

    var carregando by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        db.collection("perfil")
            .document("usuario")
            .get()
            .addOnSuccessListener { documento ->

                if (documento.exists()) {

                    nome = documento.getString("nome") ?: ""
                    modalidade = documento.getString("modalidade") ?: ""
                    objetivo = documento.getString("objetivo") ?: ""
                }

                carregando = false
            }
            .addOnFailureListener {

                carregando = false

                Toast.makeText(
                    context,
                    "Erro ao carregar perfil",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Branco)
    ) {

        BarraTitulo(
            titulo = "Meu Perfil"
        )

        if (carregando) {

            Text(
                text = "Carregando perfil...",
                color = Preto,
                modifier = Modifier.padding(24.dp)
            )

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {

                CampoTexto(
                    valor = nome,
                    titulo = "Nome",
                    onValorAlterado = {
                        nome = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                CampoTexto(
                    valor = modalidade,
                    titulo = "Modalidade principal",
                    onValorAlterado = {
                        modalidade = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                CampoTexto(
                    valor = objetivo,
                    titulo = "Objetivo",
                    onValorAlterado = {
                        objetivo = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Button(
                    onClick = {

                        val perfil = hashMapOf(
                            "nome" to nome,
                            "modalidade" to modalidade,
                            "objetivo" to objetivo
                        )

                        db.collection("perfil")
                            .document("usuario")
                            .set(perfil)
                            .addOnSuccessListener {

                                Toast.makeText(
                                    context,
                                    "Perfil salvo com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { erro ->

                                Toast.makeText(
                                    context,
                                    "Erro ao salvar perfil: ${erro.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Laranja,
                        contentColor = Preto
                    )
                ) {

                    Text(
                        text = "Salvar Perfil",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = onVoltar,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Preto,
                        contentColor = Branco
                    )
                ) {

                    Text(
                        text = "Voltar",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}