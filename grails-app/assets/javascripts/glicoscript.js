// glicoscript.js

$(document).ready(function() {
    /* ---------------------------------------------------------------------- *
     * Função para validação de campos de formulário                          *
     * String elemento (mas deve ser um id)                                   *
     * String elementoErro (mas deve ser uma classe filha de elemento)        *
     * String mensagem
     * ---------------------------------------------------------------------- */
    function validaCampoVazio(elemento, elementoErro, mensagem) {
        // detecta classe CSS da mensagem de erro e limpa o conteudo
        elementoComposto = $('#' + elemento + ' ' + '.' + elementoErro);
        
        if (elementoComposto.hasClass("errorInputField")) {
            elementoComposto.text("");
            elementoComposto.toggleClass("errorInputField");
        }
        // Busca o Input que é 'child' do elemento (que está no div)
        elementoInput = $('#' + elemento + ' input');
        if (!elementoInput.val()) {
            // se tiver erro, adiciona a classe CSS da mensagem de erro
            elementoComposto.text(mensagem);
            elementoComposto.toggleClass("errorInputField active");
            // retorna 1 para computar a qtde de erros no form
            return 1;
        } else {
            // retorna 0 se não tiver erro de validação
            return 0;
        }
    }
    
    /* ---------------------------------------------------------------------- *
     * Função para validação de Selects em formulários                        *
     * String elemento (mas deve ser um id)                                   *
     * String elementoErro (mas deve ser uma classe filha de elemento)        *
     * String mensagem                                                        *
     * ---------------------------------------------------------------------- */
    function validaSelecaoVazia(elemento, elementoErro, mensagem) {
        // detecta classe CSS da mensagem de erro e limpa o conteudo
        elementoComposto = $('#' + elemento + ' ' + '.' + elementoErro);
        
        if (elementoComposto.hasClass("errorInputField")) {
            elementoComposto.text("");
            elementoComposto.toggleClass("errorInputField");
        }
        // Busca o Input que é 'child' do elemento (que está no div)
        elementoInput = $('#' + elemento + ' select');
        if (!elementoInput.val()) {
            // se tiver erro, adiciona a classe CSS da mensagem de erro
            elementoComposto.text(mensagem);
            elementoComposto.toggleClass("errorInputField active");
            // retorna 1 para computar a qtde de erros no form
            return 1;
        } else {
            // retorna 0 se não tiver erro de validação
            return 0;
        }
    }
        
    
    // Ativa o DatePicker (Calendário) para o campo Data do formulário
    var date_input=$('input[name="dataRegistro"]'); //our date input has the name "dataRegistro"
    var date_options={
        monthsFull: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        weekdaysFull: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
        weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
        
        // Buttons
        today: 'Hoje',
        clear: 'Limpar',
        close: 'Fechar',
        
        // Formats
        format: 'dd/mm/yyyy',
        formatSubmit: 'dd/mm/yyyy',
        
        // Date limits
        max: "+0d"
      };
    date_input.pickadate(date_options);
  
    // Define uma máscara para o campo Hora do formulário
    var time_input=$('input[name="horaRegistro"]'); //our time input has the name "horaRegistro"
    var time_options={
        // Formats
        format: 'HH:i',
      };
    time_input.pickatime(time_options);

    // Conforme a opção escolhida no Tipo de Registro, mostra a seção do formulário respectiva
    var myFunction = function() {
        var v1 = $("input:radio[name ='tipoRegistro']:checked").val()
        switch(v1) {
            case 'Glicemia':
                $('#grupoGlicemia').show();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                $('input.formTipoLadoDireito').val('Glicemia');
                break;
            case 'Insulina':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').show();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                $('input.formTipoLadoDireito').val('Insulina');
                break;
            case 'Refeicao':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').show();
                $('#grupoAtivFisica').hide();
                $('input.formTipoLadoDireito').val('Refeicao');
                break;
            case 'AtivFisica':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').show();
                $('input.formTipoLadoDireito').val('AtivFisica');
                break;
            default:
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                break;
         }
    }
    
    // Conforme a opção escolhida no Tipo de Registro, mostra a seção do formulário respectiva
    // Seta evento no carregamento da página para mostrar o form quando do re-carregamento (em caso de erro de preenchimento, por exemplo)
    $(document).on ("ready", myFunction);
    // Chama a função para as mudanças no seletor de radio do tipo do registro
    $('input[type=radio][name=tipoRegistro]').on ( "change", myFunction);
    
    
    // Copia os valores do campos Data e Hora da parte esquerda do formulario para os campos da parte direita
    $('.botaoFormLadoDireito').on("mouseenter",function() {
        $('input.formDataLadoDireito').val($('#dataRegistro').val());
        $('input.formHoraLadoDireito').val($('#horaRegistro').val());
    });
    
    /* --------------------------------------------------------------------- *
     * Validação do formulário Atividade Fisica                              *
     * --------------------------------------------------------------------- */
    $("#formAtivFisica").on("submit", function(){
        var contaErros = 0;
        contaErros += validaSelecaoVazia( "divGrauAtivFisica", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divObservAtivFisica", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divHoraRegistro", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divDataRegistro", "errorField", "Campo Obrigatório"); 

        // Verifica a qtde de erros de validação. Se 0 envia o form para o servidor, senão ignora o submit
        if (contaErros == 0) {
            return true;
        } else {
            return false;
        }
    });
    
    /* --------------------------------------------------------------------- *
     * Validação do formulário Refeição                                       *
     * --------------------------------------------------------------------- */
    $("#formRefeicao").on("submit", function(){
        var contaErros = 0;
        contaErros += validaSelecaoVazia( "divTipoRefeicao", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divObservRefeicao", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divHoraRegistro", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divDataRegistro", "errorField", "Campo Obrigatório"); 

        // Verifica a qtde de erros de validação. Se 0 envia o form para o servidor, senão ignora o submit
        if (contaErros == 0) {
            return true;
        } else {
            return false;
        }
    });    
    
    
    /* --------------------------------------------------------------------- *
     * Validação do formulário Insulina                                      *
     * --------------------------------------------------------------------- */
    $("#formInsulina").on("submit", function(){
        var contaErros = 0;
        contaErros += validaSelecaoVazia( "divTipoInsulina", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divDoseInsulina", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divHoraRegistro", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divDataRegistro", "errorField", "Campo Obrigatório"); 

        // Verifica a qtde de erros de validação. Se 0 envia o form para o servidor, senão ignora o submit
        if (contaErros == 0) {
            return true;
        } else {
            return false;
        }
    });
    
    /* --------------------------------------------------------------------- *
     * Validação do formulário Glicemia                                      *
     * --------------------------------------------------------------------- */
    $("#formGlicemia").on("submit", function(){
        var contaErros = 0;
        contaErros += validaSelecaoVazia( "divTipoGlicemia", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divTaxaGlicemia", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divHoraRegistro", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divDataRegistro", "errorField", "Campo Obrigatório"); 

        // Verifica a qtde de erros de validação. Se 0 envia o form para o servidor, senão ignora o submit
        if (contaErros == 0) {
            return true;
        } else {
            return false;
        }
    });
});

