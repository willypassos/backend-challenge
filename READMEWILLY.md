VALIDADADOR DE SENHAS

SOBRE

Este projeto é uma API RESTful desenvolvida para validar senhas com base em critérios específicos de segurança. A
aplicação verifica se uma senha atende aos requisitos de robustez, garantindo que as senhas fornecidas sejam seguras
para uso.

FUNCIONALIDADES

A API valida senhas com base nos seguintes critérios:

Nove ou mais caracteres

Pelo menos 1 dígito

Pelo menos 1 letra minúscula

Pelo menos 1 letra maiúscula

Pelo menos 1 caractere especial: !@#$%^&*()-+

Não deve conter caracteres repetidos

Exemplo de uso:
{
"password": "AbTp9!fok"
}

Resposta esperada:

{
"valid": true
}

TECNOLOGIAS UTILIZDAS

Java 17

Spring Boot 3.3.4

Maven

Lombok

Jacoco (para cobertura de testes)

INSTALAÇÃO E EXECUÇÃO :

PRÉ-REQUESITOS:

Java 17+

Maven

Lombok configurado na sua IDE

Passos para rodar o projeto:

git clone https://github.com/itidigital/backend-challenge

Execute os testes e compile o projeto:

mvn clean verify

TESTES:

Para verificar a cobertura de testes, o Jacoco foi utilizado. Execute o seguinte comando para rodar os testes e gerar o
relatório de cobertura:

mvn clean verify

1. Validação de Senha 🔐

   Foi centralizada na classe PasswordValidator, aplicando o princípio de Responsabilidade Única para facilitar futuras
extensões e manutenção.

2. Testes de Unidade e Integração 🔧

   Os testes garantem a funcionalidade completa da aplicação, desde a lógica de validação até o comportamento da API,
usando Mockito para simulação de dependências.

3. Exceções Personalizadas 🎯

   Utilizamos exceções específicas para fornecer mensagens de erro mais claras e orientadas, melhorando a experiência do
usuário.


4. Cobertura de Testes com Jacoco 📊

   O Jacoco foi configurado para garantir uma alta cobertura de testes e fornecer relatórios detalhados sobre as áreas
cobertas.

5. Bean Validation ⚙️
   
   Utilizado para simplificar a validação automática de campos de entrada, garantindo que as senhas sejam validadas
   diretamente ao serem recebidas pela API.