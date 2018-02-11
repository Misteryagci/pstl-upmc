package nodes.piccolo2d;


import org.piccolo2d.PNode;
import org.piccolo2d.nodes.PImage;
import org.piccolo2d.nodes.PText;
import org.piccolo2d.util.PBounds;
import javax.swing.ImageIcon;


public class NodeContent extends PNode {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8440846339476909563L;
	private PText text;
    private PImage icon;
    private int margin=10;

    //region getters/setters
    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public PText getText(){
        return text;
    }

    public void setText(String text){
        removeChild(this.text);
        this.text = new PText(text);
        addChild(this.text);
        //this.text.setBounds(icon.getBounds().getWidth()+margin,0,this.text.getWidth(),this.text.getHeight());
        this.text.setBounds(margin,0,this.text.getWidth(),this.text.getHeight());
        
        PBounds bounds=this.getUnionOfChildrenBounds(null);
        this.setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
    }

    public PImage getIcon(){
        return icon;
    }

    //endregion
    
    public NodeContent(PText text){
        this.text=text;
        addChild(this.text);
        //this.text.setBounds(margin,margin,this.text.getWidth(),this.text.getHeight());
        this.text.setBounds(0,0,this.text.getWidth(),this.text.getHeight());
        PBounds bounds=this.getUnionOfChildrenBounds(null);
        this.setBounds(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
    }

    public String toString(){
        return text.getText();
    }
}