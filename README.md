# 🥋 Diário de Artes Marciais

Aplicativo Android desenvolvido para o registro e acompanhamento de treinos de artes marciais.

O projeto foi desenvolvido como atividade acadêmica utilizando **Kotlin**, **Jetpack Compose** e **Firebase Cloud Firestore**, permitindo realizar operações de cadastro, consulta, alteração e exclusão de informações.



## 📱 Sobre o aplicativo

O **Diário de Artes Marciais** permite que o usuário registre seus treinos e acompanhe suas informações de forma organizada.

O aplicativo possui uma identidade visual relacionada ao tema de artes marciais, utilizando principalmente as cores **laranja, preto e branco**, além de uma imagem temática na tela inicial.



## 🎯 Objetivo

O objetivo do aplicativo é permitir o gerenciamento de informações relacionadas aos treinos de artes marciais utilizando um aplicativo Android integrado ao **Firebase Cloud Firestore**.

O projeto atende às operações de **CRUD**:

- **Create (Criar):** cadastrar informações;
- **Read (Consultar):** visualizar informações cadastradas;
- **Update (Atualizar):** editar informações;
- **Delete (Excluir):** remover informações.



## 🛠️ Tecnologias utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Firebase
- Cloud Firestore
- Gradle
- Android SDK



## 🥋 Funcionalidades

### 🏠 Tela Inicial

A tela inicial apresenta as principais funcionalidades do aplicativo:

- Novo Treino
- Meus Treinos
- Meu Perfil

A identidade visual utiliza elementos relacionados ao tema de artes marciais.



### ➕ Novo Treino

Permite cadastrar um novo treino informando:

- Modalidade
- Duração
- Observações

Após clicar em **Salvar Treino**, os dados são gravados no Firebase Cloud Firestore.



### 📋 Meus Treinos

Exibe os treinos cadastrados no banco de dados.

Os dados são recuperados do Firebase Firestore e apresentados no aplicativo.

Cada treino possui as opções:

- Editar
- Excluir



### ✏️ Editar Treino

Permite alterar as informações de um treino já cadastrado.

É possível modificar:

- Modalidade
- Duração
- Observações

As alterações são atualizadas diretamente no Firebase Firestore.



### 🗑️ Excluir Treino

Permite excluir um treino cadastrado.

Após a exclusão, o registro é removido do Firebase Firestore e deixa de aparecer na lista de treinos.



### 👤 Meu Perfil

Permite cadastrar e armazenar informações do usuário:

- Nome
- Modalidade principal
- Objetivo

As informações do perfil também são armazenadas no Firebase Firestore.



## 🔥 Firebase Cloud Firestore

O aplicativo utiliza o **Firebase Cloud Firestore** para armazenar os dados.

### Coleção `treinos`

Os documentos da coleção possuem os seguintes campos:

| Campo | Descrição |
|-------| ----------|
| `modalidade` | Modalidade praticada |
| `duracao` | Duração do treino |
| `observacoes` | Observações sobre o treino |

### Coleção `perfil`

A coleção de perfil possui os seguintes campos:

| Campo | Descrição |
|-------| ----------|
| `nome` | Nome do usuário |
| `modalidade` | Modalidade principal |
| `objetivo` | Objetivo do usuário |



## 🔄 Operações CRUD

O aplicativo implementa as quatro operações fundamentais de um CRUD:

### Create

Cadastro de novos treinos e informações de perfil no Firebase Firestore.

### Read

Consulta e exibição dos treinos e informações de perfil armazenados no Firestore.

### Update

Alteração das informações de um treino já existente.

### Delete

Exclusão de um treino do Firestore.



## 🎨 Identidade Visual

A identidade visual do aplicativo foi desenvolvida de acordo com o tema de artes marciais.

As principais características são:

- 🟠 Laranja como cor principal;
- ⚫ Preto para textos e elementos de contraste;
- ⚪ Branco como cor de apoio;
- 🥋 Imagem relacionada às artes marciais na tela inicial.

A proposta visual busca representar energia, movimento e o universo das artes marciais.



## 📂 Estrutura principal

O projeto possui como principais elementos:

- `MainActivity.kt` — arquivo principal do aplicativo;
- `TelaInicial` — tela inicial;
- `TelaNovoTreino` — cadastro de treinos;
- `TelaMeusTreinos` — consulta dos treinos;
- `TelaEditarTreino` — alteração dos treinos;
- `TelaPerfil` — cadastro e consulta do perfil;
- `Treino` — modelo utilizado para representar os dados dos treinos.



## ▶️ Como executar o projeto

### Pré-requisitos

Para executar o projeto é necessário possuir:

- Android Studio;
- Android SDK;
- JDK compatível com o projeto;
- Uma conta/projeto configurado no Firebase;
- Dispositivo Android ou emulador.

### Execução

1. Clone este repositório:
   
   `https://github.com/Herley777/DiarioArtesMarciais.git`

2. Abra o projeto no Android Studio.

3. Aguarde a sincronização do Gradle.

4. Verifique a configuração do Firebase.

5. Execute o aplicativo em um dispositivo Android ou emulador.



## 🎥 Vídeo de apresentação

O vídeo apresenta o funcionamento do aplicativo, incluindo:

- Tela inicial;
- Cadastro de um treino;
- Consulta dos treinos;
- Edição de um treino;
- Exclusão de um treino;
- Cadastro do perfil;
- Firebase Cloud Firestore;
- Dados armazenados no banco de dados.

**Link do vídeo:

## 🎥 Vídeos da apresentação

### 📱 Aplicativo funcionando
https://youtube.com/shorts/lImH1e-vxpw?feature=share

### 💻 Código – MainActivity e build.gradle
https://youtu.be/7F5CAbYXYRk

### 🔥 Firebase Firestore
https://youtu.be/5dljNmS1tsU



## 📚 Requisitos da atividade

O projeto foi desenvolvido considerando os requisitos propostos na atividade:

### 1. Tema individual

Aplicativo desenvolvido com o tema **Diário de Artes Marciais**.

### 2. Identidade visual

A interface foi desenvolvida utilizando elementos visuais relacionados ao tema de artes marciais.

### 3. CRUD com Firebase Firestore

O aplicativo realiza operações de criação, consulta, atualização e exclusão de dados utilizando o Firebase Cloud Firestore.

### 4. Vídeo

Será disponibilizado um vídeo demonstrando o funcionamento do aplicativo e a utilização do banco de dados Firebase Firestore.



## 👨‍💻 Projeto acadêmico

Projeto desenvolvido para fins acadêmicos como atividade de desenvolvimento de aplicativo Android com integração ao Firebase Cloud Firestore.