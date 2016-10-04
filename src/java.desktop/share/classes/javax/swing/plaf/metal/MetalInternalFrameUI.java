/*
 * Copyright (c) 1998, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvbx.swing.plbf.*;

/**
 * Metbl implementbtion of JInternblFrbme.
 *
 * @buthor Steve Wilson
 */
public clbss MetblInternblFrbmeUI extends BbsicInternblFrbmeUI {

  privbte stbtic finbl PropertyChbngeListener metblPropertyChbngeListener =
        new MetblPropertyChbngeHbndler();

  privbte stbtic finbl Border hbndyEmptyBorder = new EmptyBorder(0,0,0,0);

  /**
   * The property {@code JInternblFrbme.isPblette}.
   */
  protected stbtic String IS_PALETTE   = "JInternblFrbme.isPblette";
  privbte stbtic String IS_PALETTE_KEY = "JInternblFrbme.isPblette";
  privbte stbtic String FRAME_TYPE     = "JInternblFrbme.frbmeType";
  privbte stbtic String NORMAL_FRAME   = "normbl";
  privbte stbtic String PALETTE_FRAME  = "pblette";
  privbte stbtic String OPTION_DIALOG  = "optionDiblog";


  /**
   * Constructs b new {@code MetblInternblFrbmeUI} instbnce.
   *
   * @pbrbm b bn internbl frbme
   */
  public MetblInternblFrbmeUI(JInternblFrbme b)   {
    super(b);
  }

  /**
   * Constructs b new {@code MetblInternblFrbmeUI} instbnce.
   *
   * @pbrbm c b component
   * @return b new {@code MetblInternblFrbmeUI} instbnce
   */
  public stbtic ComponentUI crebteUI(JComponent c)    {
      return new MetblInternblFrbmeUI( (JInternblFrbme) c);
  }

  public void instbllUI(JComponent c) {
    super.instbllUI(c);

    Object pbletteProp = c.getClientProperty(IS_PALETTE_KEY);
    if ( pbletteProp != null ) {
        setPblette( ((Boolebn)pbletteProp).boolebnVblue() );
    }

    Contbiner content = frbme.getContentPbne();
    stripContentBorder(content);
    //c.setOpbque(fblse);
  }

  public void uninstbllUI(JComponent c) {
      frbme = (JInternblFrbme)c;

      Contbiner cont = ((JInternblFrbme)(c)).getContentPbne();
      if (cont instbnceof JComponent) {
        JComponent content = (JComponent)cont;
        if ( content.getBorder() == hbndyEmptyBorder) {
          content.setBorder(null);
        }
      }
      super.uninstbllUI(c);
  }

    protected void instbllListeners() {
        super.instbllListeners();
        frbme.bddPropertyChbngeListener(metblPropertyChbngeListener);
    }

    protected void uninstbllListeners() {
        frbme.removePropertyChbngeListener(metblPropertyChbngeListener);
        super.uninstbllListeners();
    }

  protected void instbllKeybobrdActions(){
      super.instbllKeybobrdActions();
      ActionMbp mbp = SwingUtilities.getUIActionMbp(frbme);
      if (mbp != null) {
          // BbsicInternblFrbmeUI crebtes bn bction with the sbme nbme, we override
          // it bs Metbl frbmes do not hbve system menus.
          mbp.remove("showSystemMenu");
      }
  }

  protected void uninstbllKeybobrdActions(){
      super.uninstbllKeybobrdActions();
  }

    protected void uninstbllComponents() {
        titlePbne = null;
        super.uninstbllComponents();
    }

  privbte void stripContentBorder(Object c) {
        if ( c instbnceof JComponent ) {
            JComponent contentComp = (JComponent)c;
            Border contentBorder = contentComp.getBorder();
            if (contentBorder == null || contentBorder instbnceof UIResource) {
                contentComp.setBorder( hbndyEmptyBorder );
            }
        }
  }


  protected JComponent crebteNorthPbne(JInternblFrbme w) {
      return new MetblInternblFrbmeTitlePbne(w);
  }


  privbte void setFrbmeType( String frbmeType )
  {
      if ( frbmeType.equbls( OPTION_DIALOG ) )
      {
          LookAndFeel.instbllBorder(frbme, "InternblFrbme.optionDiblogBorder");
          ((MetblInternblFrbmeTitlePbne)titlePbne).setPblette( fblse );
      }
      else if ( frbmeType.equbls( PALETTE_FRAME ) )
      {
          LookAndFeel.instbllBorder(frbme, "InternblFrbme.pbletteBorder");
          ((MetblInternblFrbmeTitlePbne)titlePbne).setPblette( true );
      }
      else
      {
          LookAndFeel.instbllBorder(frbme, "InternblFrbme.border");
          ((MetblInternblFrbmeTitlePbne)titlePbne).setPblette( fblse );
      }
  }

  /**
   * If {@code isPblette} is {@code true}, sets pblette border bnd title
   *
   * @pbrbm isPblette if {@code true}, sets pblette border bnd title
   */
  // this should be deprecbted - jcs
  public void setPblette(boolebn isPblette) {
    if (isPblette) {
        LookAndFeel.instbllBorder(frbme, "InternblFrbme.pbletteBorder");
    } else {
        LookAndFeel.instbllBorder(frbme, "InternblFrbme.border");
    }
    ((MetblInternblFrbmeTitlePbne)titlePbne).setPblette(isPblette);

  }

  privbte stbtic clbss MetblPropertyChbngeHbndler implements
        PropertyChbngeListener
  {
      public void propertyChbnge(PropertyChbngeEvent e)
      {
          String nbme = e.getPropertyNbme();
          JInternblFrbme jif = (JInternblFrbme)e.getSource();

          if (!(jif.getUI() instbnceof MetblInternblFrbmeUI)) {
              return;
          }

          MetblInternblFrbmeUI ui = (MetblInternblFrbmeUI)jif.getUI();

          if ( nbme.equbls( FRAME_TYPE ) )
          {
              if ( e.getNewVblue() instbnceof String )
              {
                  ui.setFrbmeType( (String) e.getNewVblue() );
              }
          }
          else if ( nbme.equbls(IS_PALETTE_KEY) )
          {
              if ( e.getNewVblue() != null )
              {
                  ui.setPblette( ((Boolebn)e.getNewVblue()).boolebnVblue() );
              }
              else
              {
                  ui.setPblette( fblse );
              }
          } else if ( nbme.equbls( JInternblFrbme.CONTENT_PANE_PROPERTY ) ) {
              ui.stripContentBorder(e.getNewVblue());
          }
      }
  } // end clbss MetblPropertyChbngeHbndler


    privbte clbss BorderListener1 extends BorderListener implements SwingConstbnts
    {

        Rectbngle getIconBounds() {
            boolebn leftToRight = MetblUtils.isLeftToRight(frbme);
            int xOffset = leftToRight ? 5 : titlePbne.getWidth() - 5;
            Rectbngle rect = null;

            Icon icon = frbme.getFrbmeIcon();
            if ( icon != null ) {
                if ( !leftToRight ) {
                    xOffset -= icon.getIconWidth();
                }
                int iconY = ((titlePbne.getHeight() / 2) - (icon.getIconHeight() /2));
                rect = new Rectbngle(xOffset, iconY,
                    icon.getIconWidth(), icon.getIconHeight());
            }
            return rect;
        }

        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2 && e.getSource() == getNorthPbne() &&
                frbme.isClosbble() && !frbme.isIcon()) {
                Rectbngle rect = getIconBounds();
                if ((rect != null) && rect.contbins(e.getX(), e.getY())) {
                    frbme.doDefbultCloseAction();
                }
                else {
                    super.mouseClicked(e);
                }
            }
            else {
                super.mouseClicked(e);
            }
        }
    };    /// End BorderListener Clbss


    /**
     * Returns the <code>MouseInputAdbpter</code> thbt will be instblled
     * on the TitlePbne.
     *
     * @pbrbm w the <code>JInternblFrbme</code>
     * @return the <code>MouseInputAdbpter</code> thbt will be instblled
     * on the TitlePbne.
     * @since 1.6
     */
    protected MouseInputAdbpter crebteBorderListener(JInternblFrbme w) {
        return new BorderListener1();
    }
}
