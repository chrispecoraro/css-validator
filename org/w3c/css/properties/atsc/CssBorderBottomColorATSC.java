//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2005/08/08 13:18:03  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2002/07/24 14:42:28  sijtsche
 * ATSC TV profile files
 *
 * Revision 1.1  2002/05/31 09:00:16  dejong
 * ATSC TV profile objects
 *
 * Revision 3.2  1997/09/09 10:52:45  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:33  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:16  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.atsc;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderBottomColorATSC extends CssProperty {
    
    CssBorderFaceColorATSC face;
    
    /**
     * Create a new CssBorderBottomColorATSC
     */
    public CssBorderBottomColorATSC() {
	face = new CssBorderFaceColorATSC();
    }
    
    /**
     * Create a new CssBorderBottomColor with an another CssBorderFaceColor
     *
     * @param another An another face.
     */
    public CssBorderBottomColorATSC(CssBorderFaceColorATSC another) {
	
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderBottomColor
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderBottomColorATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	
	face = new CssBorderFaceColorATSC(ac, expression);
    }
    
    public CssBorderBottomColorATSC(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }
    
    /**
     * Returns the color of this property
     */
    public CssValue getColor() {
	return face.getColor();
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return face.toString();
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "border-bottom-color";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderBottomATSC bottom = ((ATSCStyle) style).cssBorderATSC.bottom;
	if (bottom.color != null)
	    style.addRedefinitionWarning(ac, this);
	bottom.color = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getBorderBottomColorATSC();
	} else {
	    return ((ATSCStyle) style).cssBorderATSC.getBottom().color;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderBottomColorATSC && face.equals(((CssBorderBottomColorATSC) property).face));
    }
    
    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	if (!face.isDefault())
	    printer.print(this);
    }
}