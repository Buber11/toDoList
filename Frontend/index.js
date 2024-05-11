'use strict';

const PageContainer = document.getElementById('container');
const select = document.getElementById('select');

window.onload = async () => {

    try {
        const response = await fetch('http://localhost:8080/api/list/get', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
                
            },
            credentials: 'include'
        });

        if (!response.ok) {
            throw new Error('Wystąpił błąd podczas pobierania danych.');
        }

        const data = await response.json();
        console.log(data); 
        processData(data.taskLists);
        
    } catch (error) {
        console.error('Wystąpił błąd:', error);
    }

}

const  processData = (taskLists) => {
    taskLists.forEach(taskList => {
        const list = new List(taskList.listId);
        list.createNewList();
        list.inputListName.value = taskList.listTitle;

        list.option.innerText = taskList.listTitle;
        select.appendChild(list.option);

        PageContainer.appendChild(list.listDiv)
        taskList.tasks.forEach(task => {
            const taskObj = new Task(list, task.taskId);
            taskObj.createNewTask(task.taskTitle);
            list.addNewTask(taskObj)
            if(task.completed == true ){
                taskObj.complete();
            }
        });
    });
}

const allList = [];
let lastRemovedList; 

class List {
    

    constructor(id){
        this.id = id;
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

        this.completedTaskList = null;
        
    }

    createNewList() {
        
        this.inputListName.classList.add('ListName');
        this.inputListName.placeholder = 'Name yout list';

        //update select option
        this.inputListName.addEventListener('blur', () =>{

            changeListTitleDataBase(this.id, this.inputListName.value); 

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
        this.listDiv.classList.add('fade-in');

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
    
        this.hideCompletedTaskList();

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

    hideCompletedTaskList(){

        if( this.complitedTaskList != null && this.completedTaskList.listTaskContainer.childElementCount === 1){
            this.completedTaskList.inputWithButton.classList.add('hidden');
            this.completedTaskList.listTaskContainer.classList.add('hidden');
        }

    }

}

class Task{

    constructor(list,id){
        this.id = id;
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
        this.list.hideCompletedTaskList();
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

    const deleteTask = (task) =>{
        task.remove();
        deleteTaskFromDataBase(task.id);
        closeModal();
    }

    confirmDeleteButton.removeEventListener('click', deleteTask);
    confirmDeleteButton.onclick = () => deleteTask(task);
  
    cancelDeleteButton.addEventListener('click', function() {
      closeModal();
    });
  
    function closeModal() {
      modalOverlay.style.display = 'none';
      modal.style.display = 'none';
    }

}

const deleteTaskFromDataBase = (taskId) => {
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8080/api/task/delete?taskId=${taskId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('An error occurred while deleting the task to the database.');
            }
            console.log('The task has been deleted from the database.');
            resolve(); 
        })
        .catch(error => {
            console.error('An error occurred:', error);
            reject(error); 
        });
    });
};



const openModalToRemoveList = (list, listTitle) => {
    const modalOverlay = document.getElementById('modal-overlay');
    const modal = document.getElementById('modal');
    const confirmDeleteButton = document.getElementById('confirm-delete');
    const cancelDeleteButton = document.getElementById('cancel-delete');
    const modalText = document.getElementById("modal-text");

    modalOverlay.style.display = 'block';
    modal.style.display = 'block';
    modalText.innerText = "Are you sure to remove list: " + listTitle + "?";

    const confirmDeleteList = async (list) =>{
        deleteListFromDataBase(list.id);
        removeListFromUI(list);
        closeModal();
    }

    confirmDeleteButton.removeEventListener('click', confirmDeleteList);

    confirmDeleteButton.onclick = () => confirmDeleteList(list);

    cancelDeleteButton.addEventListener('click', function() {
        closeModal();
    });

    function closeModal() {
        modalOverlay.style.display = 'none';
        modal.style.display = 'none';
    }

    
}

const deleteListFromDataBase = async (listId) => {
    try {
        const response = await fetch(`http://localhost:8080/api/list/delete/${listId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        });

        if (!response.ok) {
            throw new Error('An error occurred while deleting the list from the database.');
        }

        console.log('The list has been deleted from the database.');
    } catch (error) {
        console.error('An error occurred:', error);
        throw error;
    }
}

const removeListFromUI = async (list) => {
    try {
        list.listDiv.classList.remove('fade-in');
        list.listDiv.classList.add('fade-out');
        await new Promise(resolve => setTimeout(resolve, 1000)); // Wait for animation

        list.option.remove();
        allList.splice(allList.indexOf(list), 1);
        list.listDiv.remove();
    } catch (error) {
        console.error('An error occurred while removing the list from UI:', error);
        throw error;
    }
}


const input = document.getElementById('input');


const addTask = () => {
    const value = input.value;
    if (value.trim() !== '') { 
        const list = findListByOptionValue(select.value);

        const task = createNewTask(list, value);

        addTaskToDatabase(list.id, task.taskContent.textContent)
            .then(taskId => {
                task.id = taskId;
                console.log('Task added successfully. Task ID:', taskId);
            })
            .catch(error => {
                console.error('Error occurred while adding the task:', error);
            });

        input.value = ''; 
    }
};

const findListByOptionValue = (optionValue) => {
    return allList.find(list => list.option.textContent === optionValue);
};

const createNewTask = (list, taskTitle) => {
    const task = new Task(list);
    task.createNewTask(taskTitle);
    list.addNewTask(task);
    return task;
};

const addTaskToDatabase = async (listId, taskTitle) => {
    const data = {
        listId: listId,
        completed: false,
        taskTitle: taskTitle
    };

    try {
        const response = await fetch(`http://localhost:8080/api/task/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('An error occurred while adding the task to the database.');
        }

        console.log('The task has been added to the database.');
        const responseData = await response.json();
        return responseData.taskId;

    } catch (error) {
        console.error('An error occurred:', error);
        throw error; 
    }
};


const addNewList = () => {
    const list = new List();
    list.createNewList();
    PageContainer.appendChild(list.listDiv);
    
    return addListToDataBase()
        .then(listId => {
            list.id = listId;
            console.log('List added successfully. List ID:', listId);
        })
        .catch(error => {
            console.error('Error occurred while adding the list:', error);
        });
};

const addListToDataBase = async () => {
    const data = {
        listTitle: 'without name'
    };

    try {
        const response = await fetch(`http://localhost:8080/api/list/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('An error occurred while adding the list to the database.');
        }

        const responseData = await response.json();
        return responseData.listId;
    } catch (error) {
        console.error('An error occurred:', error);
        throw error; 
    }
};


const changeListTitleDataBase = (listId, newlistTitle ) =>{

    const data = {
        listTitle: newlistTitle,
        listId: listId
    }

    fetch(`http://localhost:8080/api/list/change-title`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('An error occurred while renaming the list to the database.');
        }
        
        console.log('The list has been renamed in the database.');
    })
    .catch(error => {
        console.error('An error occurred:', error);
    });
}




