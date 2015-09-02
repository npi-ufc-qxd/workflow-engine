# NPI - Estudo de Workflow Engine & BPM
Este estudo consiste no construção de uma aplicação integrada a uma *Engine de Workflow* com suporte à notação BPMN 2.0 (Business Process Management Notation), utilizando ferramentas de desenvolvimento de processo de negócio, abordando e integrando os processos internos do NPI (Núcleo de Práticas em Informática) da Universidade Federal do Ceará, no campus Quixadá.
- Prova de Conceito #1: https://adm.quixada.ufc.br/issues/1720
- Prova de Conceito #2: https://adm.quixada.ufc.br/issues/2306

#### Ferramentas pesquisadas
|Ferramenta		|Descrição													|Versão	|
|-----------------------|---------------------------------------------------------------------------------------------------------------|-------|
|Activiti BPM Platform	|Activiti is a light-weight workflow and Business Process Management (BPM) Platform targeted at business people	|5.17.0	|
|jBPM			|jBPM is a flexible Business Process Management (BPM) Suite. It makes the bridge between business analysts and developers.|6.2.0|
|Bonita BPM		|Bonita BPM for business process applications the BPM platform that gives developers freedom to create and manage highly customizable business apps. 	|6.5.2|
|**Bonita BPM**		|**Bonita BPM for business process applications the BPM platform that gives developers freedom to create and manage highly customizable business apps.** 	|**7.0.2**|
|WfMOpen		|WfMOpen is a J2EE based implementation of a workflow facility (workflow engine) as proposed by the Workflow Management Coalition (WfMC) and the Object Management Group	|2.1.2.1|

#### Ferramenta adotada
- Bonita BPM Community 6.5 (Open Solution)
- Bonita BPM Community 7.0 (Open Solution)

#### Tarefas
- [x] Preparação e configuração do ambiente local para realizar provas de conceito com ferramenta de Workflow Engine.
- [x] Instalação e Setup da ferramenta BonitaBPM Portal & BonitaBPM Studio com persistência em ambiente local.
- [x] Programação de Workflow utilizando a ferramenta Bonita BPM Studio.
- [x] Integração da Engine com uma aplicação Java Web em desenvolvimento.

##### Passos de desenvolvimento de um processo utilizando o Bonita Studio
1. Criar Diagrama
2. Definir Variáveis
3. Criar Forms
4. Especificar Atores
5. Configurar Processo
6. Executar Processo

#### Workflow patterns
- http://www.workflowpatterns.com/

### Screenshots
##### Estrutura da aplicação
![Estrutura da Aplicação](https://raw.githubusercontent.com/npi-ufc-qxd/workflow-engine/master/Bonita%20BPM/screenshots/appHome.png)

##### Aplicação em execução
![App Home](https://raw.githubusercontent.com/npi-ufc-qxd/workflow-engine/master/Bonita%20BPM/screenshots/appHome.png)
![App Dashboard](https://raw.githubusercontent.com/npi-ufc-qxd/workflow-engine/master/Bonita%20BPM/screenshots/appDashboard.png)
![App Status](https://raw.githubusercontent.com/npi-ufc-qxd/workflow-engine/master/Bonita%20BPM/screenshots/appStatus.png)

##### Suíte Bonita (Processo desenvolvido)
![Processo NPI Helpdesk](https://raw.githubusercontent.com/npi-ufc-qxd/workflow-engine/master/Bonita%20BPM/screenshots/NPI%20-%20Helpdesk-1.3.png)

### Equipe
- Thiago Pereira Rosa (Bolsista de Extensão: Núcleo de Práticas em Informática)

