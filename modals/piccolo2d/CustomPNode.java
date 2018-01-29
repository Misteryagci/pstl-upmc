package modals.piccolo2d;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.Collection;

import org.piccolo2d.PNode;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;
import org.piccolo2d.util.PBounds;

public class CustomPNode extends PNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PPath rect;
	private Collection<CustomPNode> children;
	private CustomPNode parent;
	private int margin = 10;
	private boolean expanded = false;
	private NodeContent textContent;
	private String idNode;

	public CustomPNode() {
	}

	public CustomPNode(PPath rect, Collection<CustomPNode> children, CustomPNode parent, int margin,
			String textContent) {
		super();
		this.rect = rect;
		this.children = children;
		this.parent = parent;
		this.margin = margin;
		setText(textContent);	
		rect = PPath.createRectangle(0, 0, this.textContent.getBounds().getWidth()+margin, this.textContent.getBounds().getHeight()+margin);
		addChildren(children);
		setCollapsedGridLayout();
		

	}

	public CustomPNode(PPath rect, CustomPNode parent, int margin, String textContent,String idNode) {
		super();
		this.rect = rect;
		this.parent = parent;
		this.margin = margin;
		this.idNode = idNode;
		setText(textContent);

	}

	public String getIdNode() {
		return idNode;
	}

	public void setIdNode(String idNode) {
		this.idNode = idNode;
	}

	// Getters & Setters
	public PPath getRect() {
		return rect;
	}

	public void setRect(PPath rect) {
		this.rect = rect;
	}

	public Collection<CustomPNode> getChildren() {
		return children;
	}

	public void setChildren(Collection<CustomPNode> children) {
		this.children = children;
	}

	public CustomPNode getParent() {
		return parent;
	}

	public void setParent(CustomPNode parent) {
		this.parent = parent;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	// end Getters & Setters

	public void setText(String text) {
		removeChild(this.textContent);
		this.textContent = new NodeContent(new PText(text));
		addChild(this.textContent);

	}

	public void setCollapsedGridLayout() {
		NodeContent content = this.textContent;
		for (CustomPNode customPNode : this.getChildren()) {
			customPNode.setVisible(false);
		}
		double x = 0;
		double y = 0;
		double w = margin + content.getBounds().getWidth() + margin;
		double h = margin + content.getBounds().getHeight() + margin;
		//removeChild(this.rect);
		this.rect.setBounds(x,y,w,h);
		this.parent.setGridLayoutV();
		//addChild(rect);
	}
	public void expandChildren() {
		NodeContent content = this.textContent;
		for (CustomPNode customPNode : this.getChildren()) {
			customPNode.setVisible(true);
		}
//		double x = 0;
//		double y = 0;
//		double w = margin + content.getBounds().getWidth() + margin;
//		double h = margin + content.getBounds().getHeight() + margin;
//		//removeChild(this.rect);
//		this.rect.setBounds(x,y,w,h);
		this.parent.setGridLayoutV();
	}

	public void setExpandGridLayout() {
		if(getChildren().size()==0){

            double x=0;
            double y=0;
            double w=margin+textContent.getBounds().getWidth()+margin;
            double h=margin+textContent.getBounds().getHeight()+margin;

            removeChild(rect);
            rect=PPath.createRectangle(x,y,w,h);
           // rect=bevelOut(rect,2);
            addChild(rect);
            addChild(textContent);

            return;
        }

		NodeContent content = this.textContent;
		Collection<CustomPNode> children = getChildren();

		double x = margin;
		double y = margin + content.getBounds().getHeight() + margin;
		double w = margin + content.getBounds().getWidth() + margin;
		double h = margin + content.getBounds().getHeight() + margin;

		CustomPNode lastChild = children.iterator().next();
		double maxHeight = lastChild.getRect().getHeight();
		
		  for(CustomPNode PCN:children){

	            PCN.setTransform(AffineTransform.getTranslateInstance(0,0));

	            //PCN.setExpandGridLayout();

	            PCN.translate(x,y);

	            x+=PCN.getRect().getWidth()+margin;
	            w+=PCN.getRect().getWidth()+margin;

	            if(PCN.getRect().getHeight()>maxHeight)
	                maxHeight=PCN.getRect().getHeight();
	        }
	            h+=maxHeight+margin;
	        //endregion

	        removeChild(rect);
	        rect=PPath.createRectangle(0,0,w,h);
	       // rect=bevelIn(rect,2);
	        addChild(rect);
	        addChild(content);
	        addChildren(children);
	}
	  public PPath bevelOut(PPath rectangle,int bevel){
	        double w=rectangle.getWidth();
	        double h=rectangle.getHeight();

	        PPath background = PPath.createRectangle(0,0,w,h);
	        background.setPaint(Color.WHITE);
	        PPath borderTop=PPath.createRectangle(0,0,w,bevel);
	        borderTop.setPaint(Color.LIGHT_GRAY);
	        borderTop.setStroke(null);
	        background.addChild(borderTop);
	        PPath borderLeft=PPath.createRectangle(0,0,bevel,h);
	        borderLeft.setPaint(Color.LIGHT_GRAY);
	        borderLeft.setStroke(null);
	        background.addChild(borderLeft);
	        PPath borderRight=PPath.createRectangle(w-bevel,0,bevel,h);
	        borderRight.setPaint(Color.DARK_GRAY);
	        borderRight.setStroke(null);
	        background.addChild(borderRight);
	        PPath borderBottom=PPath.createRectangle(0,h-bevel,w,bevel);
	        borderBottom.setPaint(Color.DARK_GRAY);
	        borderBottom.setStroke(null);
	        background.addChild(borderBottom);

	        return background;
	    }

	    public PPath bevelIn(PPath rectangle,int bevel){
	        double w=rectangle.getWidth();
	        double h=rectangle.getHeight();

	        PPath background = PPath.createRectangle(0,0,w,h);
	        background.setPaint(Color.WHITE);
	        PPath borderTop=PPath.createRectangle(0,0,w,bevel);
	        borderTop.setPaint(Color.DARK_GRAY);
	        borderTop.setStroke(null);
	        background.addChild(borderTop);
	        PPath borderLeft=PPath.createRectangle(0,0,bevel,h);
	        borderLeft.setPaint(Color.DARK_GRAY);
	        borderLeft.setStroke(null);
	        background.addChild(borderLeft);
	        PPath borderRight=PPath.createRectangle(w-bevel,0,bevel,h);
	        borderRight.setPaint(Color.LIGHT_GRAY);
	        borderRight.setStroke(null);
	        background.addChild(borderRight);
	        PPath borderBottom=PPath.createRectangle(0,h-bevel,w,bevel);
	        borderBottom.setPaint(Color.LIGHT_GRAY);
	        borderBottom.setStroke(null);
	        background.addChild(borderBottom);

	        return background;
	    }
	    public void setGridLayoutV(){
	        if(getChildren().size()==0){

	            double x=0;
	            double y=0;
	            double w=margin+textContent.getBounds().getWidth()+margin;
	            double h=margin+textContent.getBounds().getHeight()+margin;

	            removeChild(rect);
	            rect=PPath.createRectangle(x,y,w,h);
	            //rect=bevelOut(rect,2);
	            addChild(rect);
	            addChild(textContent);

	            return;
	        }

	        PNode content=this.textContent;
	        Collection<CustomPNode> children=getChildren();

	        double x=margin+content.getBounds().getWidth()+margin;
	        double y=0;
	        double w=margin+content.getBounds().getWidth()+margin;
	        double h=margin+content.getBounds().getHeight()+margin;

	        CustomPNode lastChild=children.iterator().next();
	        double maxWidth=lastChild.getRect().getWidth();

	        //region vertical layout
	        for(CustomPNode PCN:children){

	            PCN.setTransform(AffineTransform.getTranslateInstance(0,0));

	           // PCN.setGridLayoutV();

	            PCN.translate(x,y);

	            y+=PCN.getRect().getHeight()+margin;
	            h+=PCN.getRect().getHeight()+margin;

	            if(PCN.getRect().getWidth()>maxWidth)
	                maxWidth=PCN.getRect().getWidth();

	        }
	            w+=maxWidth+margin;
	        //endregion

	        removeChild(rect);
	        rect=PPath.createRectangle(0,0,w,h);
	      // rect=bevelIn(rect,2);
	        addChild(rect);
	        addChild(content);
	        addChildren(children);
	    }

}