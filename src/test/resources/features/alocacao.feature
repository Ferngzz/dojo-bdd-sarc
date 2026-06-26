Feature: 
	Como usuario da empresa
	Gostaria de poder alocar um recurso no sistema
	Para que eu possa realizer minhas atividades

Background: 
    Given Dado que os recursos estão cadastrados no sistema


# A alocação é feita para uma data específica e por todo o dia, 
# não podendo haver mais de uma alocação do mesmo recurso por dia.
Scenario: Recurso indisponível pelo restante do dia
    Given o "<Recurso>" já está alocado para o "<Dia>"
    When o "<Usuario>" tenta alocar o "<Recurso>" para o "<Dia>"
    Then o sistema deve informar que a alocacao nao pode ser feita


# Somente um usuário poderá fazer a alocação de um determinado recurso
# para um respectivo dia.
Scenario: Recurso é alocado para um unico usuario
    When o recurso é alocado por um Usuário
    Then o recurso estará alocado para o Usuário


# Um usuário poderá reservar um notebook e uma sala para o mesmo dia,
# mas não poderá reservar quaisquer outras combinações de recursos
# (como um laboratório junto com outro recurso).

# Happy Path
Scenario: Notebook e Sala alocados com sucesso
    Given o "<Usuario>" deseja alocar o "<Recurso1>" e a "<Recurso2>"
    When a reserva é solicitada para o "<Dia>"
    Then o sistema deve informar que a alocacao foi feita com sucesso
    | Usuario | Recurso1 | Recurso2    |
    | Bob     | Notebook | Sala        |

# Notebook e Laboratorio
Scenario: Notebook e Laboratorio não alocados
    Given o "<Usuario>" deseja alocar o "<Recurso1>" e a "<Recurso2>"
    When a reserva é solicitada para o "<Dia>"
    Then o sistema deve informar que a alocacao nao pode ser feita
    | Usuario | Recurso1 | Recurso2    |
    | Jorge   | Notebook | Laboratorio |
    | Galo    | Sala     | Laboratorio |


     