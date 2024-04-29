'use strict';



const allList = [];
const select = document.getElementById('select');
let lastRemovedList; 

class List {
    static nextId = 1;

    constructor(){
        this.id = List.nextId++;
        this.option = document.createElement('option');
        allList.push(this);
        this.taskList = [];

        this.inputListName = document.createElement('input');
        this.hideListButton = document.createElement('button');
        this.removeListButton = document.createElement("button");
        this.inputWithButton = document.createElement('div');
        
        this.listTaskContainer = document.createElement('ol');
        this.browser = document.createElement('input');

        this.listDiv = document.createElement("div");

        this.completedTaskList;
        
    }

    createNewList() {
        
        this.inputListName.classList.add('ListName');
        this.inputListName.placeholder = 'Name yout list';

        //update select option
        this.inputListName.addEventListener('blur', () =>{
            this.option.innerText = this.inputListName.value;
            if(this.option.textContent !== ""){
                select.appendChild(this.option);
            }else{
                select.removeChild(this.option);
            }
        })

        this.hideListButton.classList.add('hideListButton');
        this.hideListButton.innerHTML = "Hide";

        //implementation of hidding list
        this.hideListButton.addEventListener('click', () =>{
            if(this.listTaskContainer.classList.contains('hidden')){
                this.listTaskContainer.classList.remove('hidden');
                this.hideListButton.innerHTML = "Hide";
            }else {
                this.listTaskContainer.classList.add('hidden');
                this.hideListButton.innerHTML = "Show";
            }

        })

        this.removeListButton.classList.add('removeListButton');
        this.removeListButton.innerText = "Remove";

        //implementation of removing list
        this.removeListButton.addEventListener('click', () => {
            openModalToRemoveList(this,this.inputListName.value);
        });

        this.listTaskContainer.classList.add('listContent');

        this.browser.classList.add("browser");
        this.browser.placeholder = " < serach specific task.. > ";

        //implementation of searching specific task from the list
        this.browser.addEventListener('input', () => {
            const browersValue = this.browser.value;
            this.taskList.forEach( task => {
                if(task.taskContent.textContent.includes(browersValue)){
                    task.listElement.classList.remove('hidden');
                }else{
                    task.listElement.classList.add('hidden');
                }
            });
        })
    

        this.inputWithButton.classList.add('input-with-button');
        this.inputWithButton.appendChild(this.inputListName);
        this.inputWithButton.appendChild(this.hideListButton);
        this.inputWithButton.appendChild(this.removeListButton);

        this.listTaskContainer.appendChild(this.browser);

        this.listDiv.classList.add("list");
        this.listDiv.appendChild(this.inputWithButton);
        this.listDiv.appendChild(this.listTaskContainer);

    }

    addCompletedTaskList(list){

        list.removeListButton.remove();
        list.inputListName.value = "Complited Task";
        list.inputListName.disabled = true;
        this.completedTaskList = list;
        this.listDiv.appendChild(list.inputWithButton);
        this.listDiv.appendChild(list.listTaskContainer);

    }

    backUpTaskToList(task){
        this.addNewTask(task);
        console.log(this.completedTaskList.listTaskContainer.childElementCount)
        
        // broweser is 1 element
        if(this.completedTaskList.listTaskContainer.childElementCount === 1){
            this.completedTaskList.inputWithButton.classList.add('hidden');
            this.completedTaskList.listTaskContainer.classList.add('hidden');
        }

    }

    addNewTask(task){

        this.taskList.push(task);
        this.listTaskContainer.appendChild(task.listElement);

    }

    completeTask(task){
        const indexOftask = this.taskList.indexOf(task);
        this.taskList.splice(indexOftask,1);

        if(this.completedTaskList == null){
            const complitedTaskList = new List();
            complitedTaskList.createNewList();
            this.addCompletedTaskList(complitedTaskList);
        }else{
            this.completedTaskList.inputWithButton.classList.remove('hidden');
            this.completedTaskList.listTaskContainer.classList.remove('hidden');
        }
        
        this.completedTaskList.addNewTask(task);
    }

    removeTask(task){
        const indexOftask = this.taskList.indexOf(task);
        this.taskList.splice(indexOftask,1);
    }


}

class Task{

    constructor(list){
        this.listElement = document.createElement("li");
        this.taskContent = document.createElement('p');
        this.removeTaskButton = document.createElement('button');
        this.list = list;
    }

    createNewTask(taskContentText){
        this.listElement.classList.add("listPosition");
        
        this.taskContent.classList.add("task");
        this.taskContent.innerText = taskContentText;
    
        this.taskContent.addEventListener('click', this.complete.bind(this));
    
        this.removeTaskButton.innerText = "X"; 
        this.removeTaskButton.classList.add("removeTaskButton");
        this.removeTaskButton.addEventListener('click', () => openModalToRemoveTask(this, this.taskContent.textContent));
    
        this.listElement.appendChild(this.taskContent); 
        this.listElement.appendChild(this.removeTaskButton);
    }
    
    remove(){
        this.listElement.remove();
        this.list.removeTask(this);
    }
    
    complete(){
        this.list.completeTask(this);
        this.taskContent.removeEventListener('click', this.complete); 
        this.taskContent.addEventListener('click', this.inComplete.bind(this)); 
    }
    
    inComplete(){
        this.taskContent.removeEventListener('click', this.inComplete); 
        this.list.backUpTaskToList(this);
        this.taskContent.addEventListener('click', this.complete.bind(this));
    }
    
}

const  openModalToRemoveTask = (task, taskContent) => {
    const modalOverlay = document.getElementById('modal-overlay');
    const modal = document.getElementById('modal');
    const confirmDeleteButton = document.getElementById('confirm-delete');
    const cancelDeleteButton = document.getElementById('cancel-delete');
    const modalText = document.getElementById("modal-text");

    modalOverlay.style.display = 'block';
    modal.style.display = 'block';
    
    modalText.innerText = "Are you sure to remove task: " + taskContent + "?";

    confirmDeleteButton.addEventListener('click', function() {
        task.remove();
        closeModal();
    });
  
    cancelDeleteButton.addEventListener('click', function() {
      closeModal();
    });
  
    function closeModal() {
      modalOverlay.style.display = 'none';
      modal.style.display = 'none';
    }
}

const  openModalToRemoveList = (list, listTitle) => {
    const modalOverlay = document.getElementById('modal-overlay');
    const modal = document.getElementById('modal');
    const confirmDeleteButton = document.getElementById('confirm-delete');
    const cancelDeleteButton = document.getElementById('cancel-delete');
    const modalText = document.getElementById("modal-text");

    modalOverlay.style.display = 'block';
    modal.style.display = 'block';
    
    modalText.innerText = "Are you sure to remove list: " + listTitle + "?";

    confirmDeleteButton.addEventListener('click', function() {
        list.option.remove();
        allList.splice(allList.indexOf(this),1);
        list.listDiv.remove();
        closeModal();
    });
  
    cancelDeleteButton.addEventListener('click', function() {
      closeModal();
    });
  
    function closeModal() {
      modalOverlay.style.display = 'none';
      modal.style.display = 'none';
    }
}
const input = document.getElementById('input');


const add = () => {
    const value = input.value;
    if (value.trim() !== '') { 
        const list = allList.find( list => list.option.textContent === select.value );

        const task = new Task(list);
        task.createNewTask(value);

        list.addNewTask(task);

        input.value = ''; 
    }

};

const PageContainer = document.getElementById('container');

const addNewList = () => {
    const list = new List();
    list.createNewList();
    PageContainer.appendChild(list.listDiv);
};



