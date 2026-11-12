/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estruturas;

/**
 *
 * @author gabriel
 */
public class Pilha<T> {
    private Celula<T> topo;
    private int size;
    
    
    public Pilha(){
        this.topo =null;
        this.size =0;
        
    }
    
    public void push(T item){
        if(item == null){
            throw new IllegalArgumentException("Não é permitido elementos nulos");
        }
        
        this.topo = new Celula<T>(item, this.topo);
        this.size ++;
    }
    
    public T pop() throws Exception{
        if(this.vazia()){
            throw new Exception("Erro: A pilha está vazia");
        }
        return this.topo.getItem();
        
    }
    
    public boolean vazia(){
        return this.topo == null;
    }
    
    public int tamanho(){
        return this.size;
    }
    
    public void limpar(){
        this.topo = null;
        this.size = 0;
    }
}
