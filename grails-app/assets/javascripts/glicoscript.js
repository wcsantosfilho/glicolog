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
    var date_input=$('.dataGlico'); //our date input has the name "dataRegistro"
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
    
    /* --------------------------------------------------------------------- *
     * Montagem da Lista de Tipos de Registro Preenchidos                    *
     * --------------------------------------------------------------------- */
    // Funções para tratar do objeto que controla o preenchimento das Tabs
    // onde a Key = Tab e o Value = lista dos campos preenchidos
    // retorna a lista de campos da tab, undefined se a tab não existe
    var getTab = function (nomeTab) {
        return listaTabs[nomeTab];
    };

    var addTabECampo = function (nomeTab, nomeCampo) {
        listaCampos = [];
        tabExiste = getTab(nomeTab);
        if (tabExiste) {
            listaCampos = listaTabs[nomeTab];
            // Se o campo não está na lista, insere
            if (listaCampos.indexOf(nomeCampo) == -1) {
                listaCampos.push(nomeCampo);
            }
            listaCampos.sort();
            listaTabs[nomeTab] = listaCampos;
        } else {
            listaCampos.push(nomeCampo);
            listaTabs[nomeTab] = listaCampos;
        }
    };

    var deleteTabECampo = function (nomeTab, nomeCampo) {
        listaCampos = [];
        tabExiste = getTab(nomeTab);
        if (tabExiste) {
            listaCampos = listaTabs[nomeTab];
            // Se o campo está na lista, deleta
            posCampo = listaCampos.indexOf(nomeCampo)
            if ( posCampo !== -1) {
                listaCampos.splice( posCampo, 1)
                // Se a lista ainda tem campos, salva como valor da Tab
                if (listaCampos.length > 0 ) {
                    listaCampos.sort();
                    listaTabs[nomeTab] = listaCampos;
                } else {
                    // Senão, deleta a Tab
                    delete listaTabs[nomeTab];
                }
            }
        }
    };
    
    // Monta Objeto para indicar quais campos, de quais Tabs foram preenchidos
    // com o objetivo de chamar a gravação dos registros de cada tipo preenchido
    // listaTabs mostra as Tabs que tiveram dados preenchidos. Serve para o Insert dos registros
    // e também para validação dos campos.
    var listaTabs = {};
    // A classe CSS 'glicoInput' indica quais os Inputs que serão tratados
    
    // Define a listaTabs com os campos que vierem preenchidos do Server Side
    $('.glicoInput').filter(function() {
        if (this.value) {
            setaInputList(this)     
        }
    });
    
    // Define a listaTabs com os campos que forem preenchidos no form
    $('.glicoInput').on('change', function() {
        setaInputList(this);
    });
    
    // Monta Input List com os elementos que foram preenchidos
    function setaInputList(elementoInput) {
        var tipoRegistroInput = $(elementoInput).parents('.tab-pane').attr('id');
        var campoInput = $(elementoInput).attr('id');
        // Insere a Tab+Field preenchido no objeto
        if ($(elementoInput).val() !== '') {
                addTabECampo(tipoRegistroInput, campoInput);
                if (tipoRegistroInput == 'Glicemia') {
                    $('#iconeGlicemia').css("display", "inline");
                }
                if (tipoRegistroInput == 'Insulina') {
                    $('#iconeInsulina').css("display", "inline");
                }
                if (tipoRegistroInput == 'Refeicao') {
                    $('#iconeRefeicao').css("display", "inline");
                }
                if (tipoRegistroInput == 'AtivFisica') {
                    $('#iconeAtivFisica').css("display", "inline");
                }
        }

        // Elimina a Tab+Field, quando seu valor for excluido, no objeto (se tiver sido inserido)
        if ($(elementoInput).val() == '') {
                deleteTabECampo(tipoRegistroInput, campoInput);
                if (tipoRegistroInput == 'Glicemia') {
                    $('#iconeGlicemia').css("display", "none");
                }
                if (tipoRegistroInput == 'Insulina') {
                    $('#iconeInsulina').css("display", "none");
                }
                if (tipoRegistroInput == 'Refeicao') {
                    $('#iconeRefeicao').css("display", "none");
                }
                if (tipoRegistroInput == 'AtivFisica') {
                    $('#iconeAtivFisica').css("display", "none");
                }
        }
        $('#tipoRegistro').val(Object.keys(listaTabs));
    }

    
    /* --------------------------------------------------------------------- *
     * Validação do formulário de Entrada de Registros                       *
     * --------------------------------------------------------------------- */
    $("#formRegistro").on("submit", function(){
        // Valida campos obrigatórios do formulario: data e hora
        var contaErros = 0;
        contaErros += validaCampoVazio( "divHoraRegistro", "errorField", "Campo Obrigatório");
        contaErros += validaCampoVazio( "divDataRegistro", "errorField", "Campo Obrigatório"); 
        
        // Valida campos da tab "Atividade Fisica", se estiverem preenchidos
        if (getTab('AtivFisica')) {
            contaErros += validaSelecaoVazia( "divGrauAtivFisica", "errorField", "Campo Obrigatório");
            contaErros += validaCampoVazio( "divObservAtivFisica", "errorField", "Campo Obrigatório");
        }

        // Valida campos da tab "Refeição", se estiverem preenchidos
        if (getTab('Refeicao')) {
            contaErros += validaSelecaoVazia( "divTipoRefeicao", "errorField", "Campo Obrigatório");
            contaErros += validaCampoVazio( "divObservRefeicao", "errorField", "Campo Obrigatório");
        }

        // Valida campos da tab "Insulina", se estiverem preenchidos
        if (getTab('Insulina')) {
            contaErros += validaSelecaoVazia( "divTipoInsulina", "errorField", "Campo Obrigatório");
            contaErros += validaCampoVazio( "divDoseInsulina", "errorField", "Campo Obrigatório");
        }

        // Valida campos da tab "Glicemia", se estiverem preenchidos
        if (getTab('Glicemia')) {
            contaErros += validaSelecaoVazia( "divTipoGlicemia", "errorField", "Campo Obrigatório");
            contaErros += validaCampoVazio( "divTaxaGlicemia", "errorField", "Campo Obrigatório");
        }
        
        // Verifica a qtde de erros de validação. Se 0 envia o form para o servidor, senão ignora o submit
        if (contaErros == 0) {
            return true;
        } else {
            $('#errorMessage').text("O formulário contem erros!")
            return false;
        }
    });

    /* --------------------------------------------------------------------- *
     * Validação do formulário de Entrada de Registros                       *
     * --------------------------------------------------------------------- */
    $("#formRelatorios").on("submit", function(){
        // Valida form relatórios
        console.log($("#dataIni"))
        console.log($("#dataFim"))
    });

    /* --------------------------------------------------------------------- *
     * Função para calcular data no passado                                  *
     * Parametros:                                                           *
     * |- data = data atual                                                  *
     * |- anos = anos a retroceder                                           *
     * |- meses = meses a retroceder                                         *
     * |- dias = dias a retroceder                                           *
     * Exemplo: (data, 1, 2, 3) => retrocede 1 ano, 2 meses e 3 dias         *
     * --------------------------------------------------------------------- */
    function calculaDataPassada(data, anos, meses, dias) {
        diaAtual = data.getDate();
        mesAtual = data.getMonth();
        anoAtual = data.getFullYear();
        if (meses > 12) {
            anos = anos + (meses % 12)
            meses = meses - (anos * 12)
        }
        anoPassado = anoAtual - anos;
        mesPassado = mesAtual - meses;
        if (mesPassado < 0) {
            anoPassado = anoPassado - 1;
            mesPassado = 12 + mesPassado;
        }
        diaPassado = diaAtual - dias;
        dataPassada = new Date()
        dataPassada.setFullYear(anoPassado)
        dataPassada.setMonth(mesPassado)
        dataPassada.setDate(diaPassado)
        
        return dataPassada;
    }    
    
    /* --------------------------------------------------------------------- *
     * Validacao do intervalo de datas                                       *
     * --------------------------------------------------------------------- */
    function validaIntervaloForm() {
            $("#erroFormData").text("")
            $("#botaoFormData").prop("disabled",false);
            
            dataIniSelecionada = $("#dataIni").pickadate().pickadate('picker').get('select');
            dataFimSelecionada = $("#dataFim").pickadate().pickadate('picker').get('select');
            if (dataFimSelecionada != null && dataIniSelecionada != null) {
                if ((dataFimSelecionada.pick < dataIniSelecionada.pick) ) {
                    $("#erroFormData").text("Erro no intervalo de datas")
                    $("#erroFormData").css("color", "#ff0000")
                    $("#botaoFormData").prop("disabled",true);
                } 
            } else {
                $("#botaoFormData").prop("disabled",true);
            }
            event.preventDefault();
    }    

    /* --------------------------------------------------------------------- *
     * Validação e Definição do seletor de datas no cabecalho para relatório *
     * --------------------------------------------------------------------- */
    $("#button3Meses").on("click", function() {
        var dataAtual = new Date();
        var data3Meses = calculaDataPassada(dataAtual, 0, 3 , 0);
        data3Meses.setDate(1);

        // Chama a API do Pickadate para "setar" a data
        var $input = $('#dataIni').pickadate();
        var picker = $input.pickadate('picker');
        picker.set('select', data3Meses);
        
        var $input = $('#dataFim').pickadate();
        var picker = $input.pickadate('picker');
        picker.set('select', dataAtual);
        return false;
    });
    
    $("#buttonUltMes").on("click", function() {
        var dataAtual = new Date();
        var dataUltMes = calculaDataPassada(dataAtual, 0, 1 , 0);
        dataUltMes.setDate(1);

        // Chama a API do Pickadate para "setar" a data
        var $input = $('#dataIni').pickadate();
        var picker = $input.pickadate('picker');
        picker.set('select', dataUltMes);
        
        dataAtual = calculaDataPassada(dataAtual,0, 0, dataAtual.getDate())
        var $input = $('#dataFim').pickadate();
        var picker = $input.pickadate('picker');
        picker.set('select', dataAtual);
        return false;
    });
    
    
    // Validação se a DataFim é Maior que a DataIni
    var $input = $('#dataIni').pickadate();
    var pickerIni = $input.pickadate('picker');
    dataIniSelecionada = pickerIni.get();
    pickerIni.on({
        set: function(dataSelecionada) {
            validaIntervaloForm()
        }
    });

    var $input = $('#dataFim').pickadate();
    var pickerFim = $input.pickadate('picker');
    dataFimSelecionada = pickerFim.get();
    
    pickerFim.on({
        set: function(dataSelecionada) {
            validaIntervaloForm()
        }
    });
    


});

