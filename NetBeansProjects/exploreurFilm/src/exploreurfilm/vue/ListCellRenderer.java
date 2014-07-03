/*
 * Copyright (c) 1998, 2007, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package exploreurfilm.vue;

import exploreurfilm.StringMaker;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.awt.Component;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.Serializable;
import sun.swing.DefaultLookup;

/**
 * Renders an item in a list.
 * <p>
 * <strong><a name="override">Implementation Note:</a></strong>
 * This class overrides <code>invalidate</code>, <code>validate</code>,
 * <code>revalidate</code>, <code>repaint</code>, <code>isOpaque</code>, and
 * <code>firePropertyChange</code> solely to improve performance. If not
 * overridden, these frequently called methods would execute code paths that are
 * unnecessary for the default list cell renderer. If you write your own
 * renderer, take care to weigh the benefits and drawbacks of overriding these
 * methods.
 *
 * <p>
 *
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with future Swing
 * releases. The current serialization support is appropriate for short term
 * storage or RMI between applications running the same version of Swing. As of
 * 1.4, support for long term storage of all JavaBeans<sup><font
 * size="-2">TM</font></sup>
 * has been added to the <code>java.beans</code> package. Please see
 * {@link java.beans.XMLEncoder}.
 *
 * @author Philip Milne
 * @author Hans Muller
 */
public class ListCellRenderer<T> extends JLabel
        implements javax.swing.ListCellRenderer<T>, Serializable {

    /**
     * An empty <code>Border</code>. This field might not be used. To change the
     * <code>Border</code> used by this renderer override the
     * <code>getListCellRendererComponent</code> method and set the border of
     * the returned component directly.
     */
    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;
    private StringMaker<T> sm;
    private int i;
    private MouseAdapter handler;

    /**
     * Constructs a default renderer object for an item in a list.
     */
    public ListCellRenderer(StringMaker<T> stringMaker) {
        super();
        this.sm = stringMaker;
        setOpaque(true);
        setBorder(getNoFocusBorder());
        setName("List.cellRenderer");
    }

    private Border getNoFocusBorder() {
        Border border = DefaultLookup.getBorder(this, ui, "List.cellNoFocusBorder");
        if (System.getSecurityManager() != null) {
            if (border != null) {
                return border;
            }
            return SAFE_NO_FOCUS_BORDER;
        } else {
            if (border != null
                    && (noFocusBorder == null
                    || noFocusBorder == DEFAULT_NO_FOCUS_BORDER)) {
                return border;
            }
            return noFocusBorder;
        }
    }

    public Component getListCellRendererComponent(
            JList<? extends T> list,
            T value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        i++;
//        System.out.println("*******");
//        System.out.println(isSelected);
//        System.out.println(cellHasFocus);
//        System.out.println(index);
//        System.out.println(i);
//        System.out.println(i);
        setComponentOrientation(list.getComponentOrientation());

        Color bg = null;
        Color fg = null;

        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            bg = DefaultLookup.getColor(this, ui, "List.dropCellBackground");
            fg = DefaultLookup.getColor(this, ui, "List.dropCellForeground");

            isSelected = true;
        }

        if (isSelected) {
            setBackground(bg == null ? list.getSelectionBackground() : bg);
            setForeground(fg == null ? list.getSelectionForeground() : fg);
        } else {
            if (hoverIndex != index) {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            } else {
                setBackground(bg == null ? list.getSelectionBackground() : bg);
                setForeground(fg == null ? list.getSelectionForeground() : fg);
            }

        }

        if (value instanceof Icon) {
            setIcon((Icon) value);
            setText("");
        } else {
            setIcon(null);
            setText((value == null) ? "" : sm.buildString(value));
            setToolTipText(sm.buildString(value));
        }

        setEnabled(list.isEnabled());
        setFont(list.getFont());

        Border border = null;
        if (cellHasFocus) {
            if (isSelected) {
                border = DefaultLookup.getBorder(this, ui, "List.focusSelectedCellHighlightBorder");
            }
            if (border == null) {
                border = DefaultLookup.getBorder(this, ui, "List.focusCellHighlightBorder");
            }
        } else {
            border = getNoFocusBorder();
        }
//        if (!isSelected) {
//            if(index == hoverIndex){
//                
//            }
//            setBackground(index == hoverIndex
//                    ? HOVER_COLOR : list.getBackground());
//        }
        setBorder(border);

        return this;
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     *
     * @since 1.5
     * @return <code>true</code> if the background is completely opaque and
     * differs from the JList's background; <code>false</code> otherwise
     */
    @Override
    public boolean isOpaque() {
        Color back = getBackground();
        Component p = getParent();
        if (p != null) {
            p = p.getParent();
        }
        // p should now be the JList.
        boolean colorMatch = (back != null) && (p != null)
                && back.equals(p.getBackground())
                && p.isOpaque();
        return !colorMatch && super.isOpaque();
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void validate() {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     *
     * @since 1.5
     */
    @Override
    public void invalidate() {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     *
     * @since 1.5
     */
    @Override
    public void repaint() {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void revalidate() {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void repaint(long tm, int x, int y, int width, int height) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void repaint(Rectangle r) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        // Strings get interned...
        if (propertyName == "text"
                || ((propertyName == "font" || propertyName == "foreground")
                && oldValue != newValue
                && getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey) != null)) {

            super.firePropertyChange(propertyName, oldValue, newValue);
        }
    }

    public MouseAdapter getHandler(JList list) {
        if (handler == null) {
            handler = new HoverMouseHandler(list);
        }
        return handler;
    }
    private int hoverIndex = -1;

    class HoverMouseHandler extends MouseAdapter {

        private final JList list;

        public HoverMouseHandler(JList list) {
            this.list = list;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setHoverIndex(-1);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int index = list.locationToIndex(e.getPoint());
            setHoverIndex(list.getCellBounds(index, index).contains(e.getPoint())
                    ? index : -1);
        }

        private void setHoverIndex(int index) {
            if (hoverIndex == index) {
                return;
            }
            hoverIndex = index;
            list.repaint();
        }
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, byte oldValue, byte newValue) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, char oldValue, char newValue) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, short oldValue, short newValue) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, int oldValue, int newValue) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, long oldValue, long newValue) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, float oldValue, float newValue) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, double oldValue, double newValue) {
    }

    /**
     * Overridden for performance reasons. See the <a
     * href="#override">Implementation Note</a>
     * for more information.
     */
    @Override
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
    }

    /**
     * A subclass of DefaultListCellRenderer that implements UIResource.
     * DefaultListCellRenderer doesn't implement UIResource directly so that
     * applications can safely override the cellRenderer property with
     * DefaultListCellRenderer subclasses.
     * <p>
     * <strong>Warning:</strong>
     * Serialized objects of this class will not be compatible with future Swing
     * releases. The current serialization support is appropriate for short term
     * storage or RMI between applications running the same version of Swing. As
     * of 1.4, support for long term storage of all JavaBeans<sup><font
     * size="-2">TM</font></sup>
     * has been added to the <code>java.beans</code> package. Please see
     * {@link java.beans.XMLEncoder}.
     */
    public static class UIResource extends DefaultListCellRenderer
            implements javax.swing.plaf.UIResource {
    }
}
