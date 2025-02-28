class Grid{
    #rows_nb;
    #columns_nb;
    constructor(rows_nb, columns_nb){
        this.#rows_nb = rows_nb;
        this.#columns_nb = columns_nb;
        this.changeGrid(rows_nb, columns_nb);
    }
    
    changeGrid(new_rows_nb, new_columns_nb){
        this.#rows_nb = new_rows_nb;
        this.#columns_nb = new_columns_nb;
        this.#updateGrid();
    }


    #updateGrid(){
        let tbody = document.getElementById('tbody');
        let rows = tbody.childNodes;
        for(let i=0; i<rows.length;){
            tbody.removeChild(rows.item(i));
        }

        /* Ajout des lettres au dessus de la grille */
        let row = document.createElement('tr');
        row.setAttribute("class", "row-cell");
        for(let j=0; j < parseInt(this.#columns_nb) + 1; j++){
            let letter_column = document.createElement('td');
            letter_column.setAttribute("class", "letter-index");
            let letter = (j == 0) ? "" : String.fromCharCode("A".charCodeAt(0) + (j - 1));
            let textNode = document.createTextNode(letter);
            letter_column.appendChild(textNode);
            row.appendChild(letter_column);
        }
        tbody.appendChild(row);


        /* Création des cases */
        for(let i=0; i < this.#rows_nb; i++){
            let row = document.createElement('tr');
            row.setAttribute("class", "row-cell");

            let number_column = document.createElement('td');
            number_column.setAttribute("class", "number-index");
            let textNode = document.createTextNode(i + 1);
            number_column.appendChild(textNode);
            row.appendChild(number_column);

            for(let j=0; j < this.#columns_nb; j++){
                let column = document.createElement('td');
                let input = document.createElement('input');
                input.setAttribute("name", (i + 1) + "-" + String.fromCharCode("A".charCodeAt(0) + j));
                input.setAttribute("class", "cell");
                input.setAttribute("type", "text");
                input.setAttribute("maxlength", "1");
                input.setAttribute("oninput", "caseTreatement(this)");
                input.setAttribute("value", "");
                column.appendChild(input);
                row.appendChild(column);
            }
            tbody.appendChild(row);
        }
        blackCase();
    }


    // Renvoie un tableau contenant les chaines de caractères de chaque ligne de la grille
    readRows(){
        let cells = document.getElementsByClassName('cell');
        let cell_nb = 0;
        let tab = [];
        for(let i=0; i < this.#rows_nb; i++){
            let row = "";
            for(let j=0; j < this.#columns_nb; j++){
                let value = (cells.item(cell_nb)).getAttribute('value');
                if(value == ''){
                    row+=' ';
                }else{
                    row+=value;
                }
                cell_nb+=1;
            }
            tab.push(row);  
        }
        return tab;
    }

    // Renvoie un tableau contenant les chaines de caractères de chaque colonne de la grille
    readColumns(){
        let cells = document.getElementsByClassName('cell');
        let jump_nb = parseInt(this.#columns_nb);
        let tab = [];

        for(let i=0; i < this.#columns_nb; i++){
            let column = "";
            let value = (cells.item(i)).getAttribute('value');
            column+=(value == '') ? ' ' : value;

            for(let k=jump_nb; k < this.#rows_nb * this.#columns_nb; k+=(jump_nb)){
                value = (cells.item(k + i)).getAttribute('value');
                column+=(value == '') ? ' ' : value;
            }
            tab.push(column);
        }
        return tab;
    }

    initRowsHints(){
        let horizontal_hints = document.getElementById('rows-hints');
        let childs = horizontal_hints.childNodes;
        for(let i=0; i < childs.length;){
            horizontal_hints.removeChild(childs.item(i));
        }
        for(let i = 0; i < this.#rows_nb; i++){

            let hints_row = document.createElement('p');
            hints_row.setAttribute("class", "hints-row-" + (i + 1));
            let hints_text = document.createTextNode((i + 1) + ' - ');
            hints_row.appendChild(hints_text);

            let hint = document.createElement('input');
            hint.setAttribute("type", "text");
            hint.setAttribute("class", "hint");
            hints_row.appendChild(hint);
                    
            horizontal_hints.appendChild(hints_row);
        }
    }

    initColumnsHints(){
        let vertical_hints = document.getElementById('columns-hints');
        let childs = vertical_hints.childNodes;
        for(let i=0; i < childs.length;){
            vertical_hints.removeChild(childs.item(i));
        }
        for(let i = 0; i < this.#columns_nb; i++){

            let hints_column = document.createElement('p');
            hints_column.setAttribute("class", "hints-column-" + String.fromCharCode("A".charCodeAt(0) + i) );
            let hints_text = document.createTextNode(String.fromCharCode("A".charCodeAt(0) + i) + " - ");
            hints_column.appendChild(hints_text);

            let hint = document.createElement('input');
            hint.setAttribute("type", "text");
            hint.setAttribute("class", "hint");
            hints_column.appendChild(hint);
                    
            vertical_hints.appendChild(hints_column);
        }
    }

    addRowHints(name){
        let row_number = (name.split('-'))[0];
        let row_hints = document.getElementsByClassName('hints-row-' + row_number);
        row_hints = row_hints[0];
        let childs = row_hints.childNodes;
        for(let i=0; i < childs.length;){
            row_hints.removeChild(childs.item(i));
        }
        let textNode = document.createTextNode(row_number + " - ");
        row_hints.appendChild(textNode);

        let tab_row = this.readRows();
        let row = tab_row[row_number - 1];

        let tab_word = row.split('$');
        for(let word of tab_word){
            if(word.length >= 2){
                if((row_hints.childNodes).length >= 2){
                    let blackSquare = document.createTextNode(" " + "\u25A0" + " ");
                    row_hints.appendChild(blackSquare);
                }
                let hint = document.createElement('input');
                hint.setAttribute("type", "text");
                hint.setAttribute("class", "hint");
                row_hints.appendChild(hint);
                
            }
        }
    }

    addColumnHints(name){
        let column_letter = (name.split('-'))[1];
        let column_hints = document.getElementsByClassName('hints-column-' + column_letter);
        column_hints = column_hints[0];
        let childs = column_hints.childNodes;
        for(let i=0; i < childs.length;){
            column_hints.removeChild(childs.item(i));
        }
        let textNode = document.createTextNode(column_letter + " - ");
        column_hints.appendChild(textNode);

        let tab_column = this.readColumns();
        let index = (column_letter.charCodeAt(0)) - ('A'.charCodeAt(0));
        let column = tab_column[index];

        let tab_word = column.split('$');
        for(let word of tab_word){
            if(word.length >= 2){
                if((column_hints.childNodes).length >= 2){
                    let blackSquare = document.createTextNode(" " + "\u25A0" + " ");
                    column_hints.appendChild(blackSquare);
                }
                let hint = document.createElement('input');
                hint.setAttribute("type", "text");
                hint.setAttribute("class", "hint");
                column_hints.appendChild(hint);
                
            }
        }
    }

    readRowsHints(){
        let res = [];
        let rows_hints = document.getElementById('rows-hints');
        rows_hints = rows_hints.childNodes;
        for(let row_hints of rows_hints){
            row_hints = row_hints.childNodes;
            let row_hints_tab = [];
            for(let hint of row_hints){
                if(hint.nodeType == '1'){
                    row_hints_tab.push(hint.value);
                }
            }
            res.push(row_hints_tab);
            
        }
        return res;
    }

    readColumnsHints(){
        let res = [];
        let columns_hints = document.getElementById('columns-hints');
        columns_hints = columns_hints.childNodes;
        for(let column_hint of columns_hints){
            column_hint = column_hint.childNodes;
            let column_hint_tab = [];
            for(let hint of column_hint){
                if(hint.nodeType == '1'){
                    column_hint_tab.push(hint.value);
                }
            }
            res.push(column_hint_tab);
            
        }
        return res;
    }

}
let grid = new Grid(2, 2);


function caseTreatement(cell){
    if(cell.style.backgroundColor == 'black'){
        cell.value = '';
    }else{
        cell.value = cell.value.toUpperCase().replace(/[^A-Za-z]/g, '');
        cell.setAttribute("value", cell.value);
    }
}
function blackCase(){
    let cells = document.getElementsByClassName('cell');
    for(let cell of cells){
        cell.addEventListener('contextmenu', function(event){
            event.preventDefault();
            if(cell.style.backgroundColor == 'black'){
                cell.style.backgroundColor = 'white';
                cell.setAttribute("value", "");
                grid.addRowHints(cell.name);
                grid.addColumnHints(cell.name);
            }else{
                cell.value = '';
                cell.style.backgroundColor = 'black';
                cell.setAttribute("value", "$");
                grid.addRowHints(cell.name);
                grid.addColumnHints(cell.name);
            }
            
        })
    }

}


function gridDimensions(){
    
    let rows_nb = document.getElementById('dim-horizontal').value;
    let columns_nb = document.getElementById('dim-vertical').value;
    grid.changeGrid(rows_nb, columns_nb);
    grid.initRowsHints();
    grid.initColumnsHints();


}

// Tools



function main(){
    grid.initRowsHints();
    grid.initColumnsHints();
}



main();
