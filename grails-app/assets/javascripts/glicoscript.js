// glicoscript.js

$(document).ready(function() {
    
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
    $('.botaoFormLadoDireito').click(function() {
        $('input.formDataLadoDireito').val($('#dataRegistro').val());
        $('input.formHoraLadoDireito').val($('#horaRegistro').val());
    });

});