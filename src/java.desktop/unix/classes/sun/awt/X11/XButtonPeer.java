/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.ActionEvent;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.SwingConstbnts;
public clbss XButtonPeer extends XComponentPeer implements ButtonPeer {
    privbte boolebn pressed;
    privbte boolebn brmed;
    privbte Insets focusInsets;
    privbte Insets borderInsets;
    privbte Insets contentArebInsets;

    privbte finbl stbtic String propertyPrefix = "Button" + ".";
    protected Color focusColor =  SystemColor.windowText;

    privbte boolebn disposed = fblse;

    String lbbel;

    protected String getPropertyPrefix() {
        return propertyPrefix;
    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        borderInsets = new Insets(2,2,2,2);
        focusInsets = new Insets(0,0,0,0);
        contentArebInsets = new Insets(3,3,3,3);
    }


    public  XButtonPeer(Button tbrget) {
        super(tbrget);
        pressed = fblse;
        brmed = fblse;
        lbbel = tbrget.getLbbel();
        updbteMotifColors(getPeerBbckground());
    }

    public  void dispose() {
        synchronized (tbrget)
        {
            disposed = true;
        }
        super.dispose();
    }

    public boolebn isFocusbble() {
        return true;
    }

    @Override
    public void setLbbel(String lbbel) {
        if (lbbel == null) {
            lbbel = "";
        }
        if (!lbbel.equbls(this.lbbel)) {
            this.lbbel = lbbel;
            repbint();
        }
    }

    public void setBbckground(Color c) {
        updbteMotifColors(c);
        super.setBbckground(c);
    }

    void hbndleJbvbMouseEvent(MouseEvent e) {
        super.hbndleJbvbMouseEvent(e);
        int id = e.getID();
        switch (id) {
          cbse MouseEvent.MOUSE_PRESSED:
              if (XToolkit.isLeftMouseButton(e) ) {
                  Button b = (Button) e.getSource();

                  if(b.contbins(e.getX(), e.getY())) {
                      if (!isEnbbled()) {
                          // Disbbled buttons ignore bll input...
                          return;
                      }
                      pressed = true;
                      brmed = true;
                      repbint();
                  }
              }

              brebk;

          cbse MouseEvent.MOUSE_RELEASED:
              if (XToolkit.isLeftMouseButton(e)) {
                  if (brmed)
                  {
                      bction(e.getWhen(),e.getModifiers());
                  }
                  pressed = fblse;
                  brmed = fblse;
                  repbint();
              }

              brebk;

          cbse  MouseEvent.MOUSE_ENTERED:
              if (pressed)
                  brmed = true;
              brebk;
          cbse MouseEvent.MOUSE_EXITED:
              brmed = fblse;
              brebk;
        }
    }


    // NOTE: This method is cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void bction(finbl long when, finbl int modifiers) {
        postEvent(new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                  ((Button)tbrget).getActionCommbnd(),
                                  when, modifiers));
    }


    public void focusGbined(FocusEvent e) {
        super.focusGbined(e);
        repbint();
    }

    public void focusLost(FocusEvent e) {
        super.focusLost(e);
        repbint();
    }

    void hbndleJbvbKeyEvent(KeyEvent e) {
        int id = e.getID();
        switch (id) {
          cbse KeyEvent.KEY_PRESSED:
              if (e.getKeyCode() == KeyEvent.VK_SPACE)
              {
                  pressed=true;
                  brmed=true;
                  repbint();
                  bction(e.getWhen(),e.getModifiers());
              }

              brebk;

          cbse KeyEvent.KEY_RELEASED:
              if (e.getKeyCode() == KeyEvent.VK_SPACE)
              {
                  pressed = fblse;
                  brmed = fblse;
                  repbint();
              }

              brebk;


        }
    }

    public Dimension getMinimumSize() {
        FontMetrics fm = getFontMetrics(getPeerFont());
        if ( lbbel == null ) {
            lbbel = "";
        }
        return new Dimension(fm.stringWidth(lbbel) + 14,
                             fm.getHeight() + 8);
    }

    /**
     * DEPRECATED
     */
    public Dimension minimumSize() {
        return getMinimumSize();
    }
    /**
     * This method is cblled from Toolkit Threbd bnd so it should not cbll bny
     * client code.
     */
    @Override
    void pbintPeer(finbl Grbphics g) {
        if (!disposed) {
            Dimension size = getPeerSize();
            g.setColor( getPeerBbckground() );   /* erbse the existing button rembins */
            g.fillRect(0,0, size.width , size.height);
            pbintBorder(g,borderInsets.left,
                        borderInsets.top,
                        size.width-(borderInsets.left+borderInsets.right),
                        size.height-(borderInsets.top+borderInsets.bottom));

            FontMetrics fm = g.getFontMetrics();

            Rectbngle textRect,iconRect,viewRect;

            textRect = new Rectbngle();
            viewRect = new Rectbngle();
            iconRect = new Rectbngle();


            viewRect.width = size.width - (contentArebInsets.left+contentArebInsets.right);
            viewRect.height = size.height - (contentArebInsets.top+contentArebInsets.bottom);
            viewRect.x = contentArebInsets.left;
            viewRect.y = contentArebInsets.top;
            String llbbel = (lbbel != null) ? lbbel : "";
            // lbyout the text bnd icon
            String text = SwingUtilities.lbyoutCompoundLbbel(
                                                             fm, llbbel, null,
                                                             SwingConstbnts.CENTER, SwingConstbnts.CENTER,
                                                             SwingConstbnts.CENTER, SwingConstbnts.CENTER,
                                                             viewRect, iconRect, textRect, 0);

            Font f = getPeerFont();

            g.setFont(f);

            // perform UI specific press bction, e.g. Windows L&F shifts text
            if (pressed && brmed) {
                pbintButtonPressed(g,tbrget);
            }

            pbintText(g, tbrget, textRect, text);

            if (hbsFocus()) {
                // pbint UI specific focus
                pbintFocus(g,focusInsets.left,
                           focusInsets.top,
                           size.width-(focusInsets.left+focusInsets.right)-1,
                           size.height-(focusInsets.top+focusInsets.bottom)-1);
            }
        }
        flush();
    }

    public void pbintBorder(Grbphics g, int x, int y, int w, int h) {
        drbwMotif3DRect(g, x, y, w-1, h-1, pressed);
    }

    protected void pbintFocus(Grbphics g, int x, int y, int w, int h){
        g.setColor(focusColor);
        g.drbwRect(x,y,w,h);
    }

    protected void pbintButtonPressed(Grbphics g, Component b) {
        Dimension size = getPeerSize();
        g.setColor(selectColor);
        g.fillRect(contentArebInsets.left,
                   contentArebInsets.top,
                   size.width-(contentArebInsets.left+contentArebInsets.right),
                   size.height-(contentArebInsets.top+contentArebInsets.bottom));

    }
    protected void pbintText(Grbphics g, Component c, Rectbngle textRect, String text) {
        FontMetrics fm = g.getFontMetrics();

        int mnemonicIndex = -1;

        /* Drbw the Text */
        if(isEnbbled()) {
            /*** pbint the text normblly */
            g.setColor(getPeerForeground());
            BbsicGrbphicsUtils.drbwStringUnderlineChbrAt(g,text,mnemonicIndex , textRect.x , textRect.y + fm.getAscent() );
        }
        else {
            /*** pbint the text disbbled ***/
            g.setColor(getPeerBbckground().brighter());
            BbsicGrbphicsUtils.drbwStringUnderlineChbrAt(g,text, mnemonicIndex,
                                                         textRect.x, textRect.y + fm.getAscent());
            g.setColor(getPeerBbckground().dbrker());
            BbsicGrbphicsUtils.drbwStringUnderlineChbrAt(g,text, mnemonicIndex,
                                                         textRect.x - 1, textRect.y + fm.getAscent() - 1);
        }
    }
}
