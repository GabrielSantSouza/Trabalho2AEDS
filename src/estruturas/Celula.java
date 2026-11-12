/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estruturas;

/**
 *
 * @author gabriel
 */
public class Celula <T>{
    private T item;
    private Celula <T> prox;
    
    public Celula(){
        this.item =null;
        this.prox = null;
    }
    
    public Celula(T item){
        this.item = item;
        this.prox = null;
    }
    
    public Celula(T item, Celula<T> prox){
        this.item = item;
        this.prox = prox;
    }
    
    public T getItem(){
        return this.item;
        
    }
    
    public void setItem(T item){
        this.item = item;
    }
    
    public Celula<T> getProx(){
       return this.prox;
    }
    
    public void setProx(Celula<T> prox){
        this.prox = prox;
        
    }
    
}
