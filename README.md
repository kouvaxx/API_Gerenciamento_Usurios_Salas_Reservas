API de Gerenciamento de Usuários, Salas e Reservas

Esta API oferece uma solução completa para gerenciar usuários, salas e reservas, possibilitando desde o cadastro e consulta até a exclusão de registros. Através dela, é possível listar todas as informações, visualizar detalhes específicos e verificar reservas associadas a um usuário ou a uma sala.
Funcionalidades Principais

    Cadastro e Consulta: Criação, listagem detalhada e consulta de usuários, salas e reservas.
    Exclusão de Registros: Remoção de usuários, salas e reservas quando necessário.
    Listagem e Detalhamento: Exibição de todas as informações com filtros específicos para reservas de usuários ou de salas.

Regras de Negócio

Para garantir a integridade das reservas, foram implementadas validações:

    Validação de Existência: Confirmação da existência do usuário e da sala antes de confirmar uma reserva.
    Bloqueio de Datas Passadas: Impede reservas para datas que já passaram.
    Limitação de Futuro: Permite reservas somente até 30 dias no futuro.
    Verificação de Disponibilidade: Garante que a sala esteja livre para a data solicitada.
    Restrição de Reserva Diária do Usuário: Confirma que o usuário não possui outra reserva para o mesmo dia.

Métodos HTTP Utilizados

    POST: Criar novos registros.
    GET: Consultar dados de usuários, salas e reservas.
    PUT: Atualizar informações de registros existentes.
    DELETE: Excluir registros.
