/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.swing;

import com.sun.bwt.AWTUtilities;
import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;

import jbvb.bwt.*;
import jbvb.bebns.PropertyVetoException;

/** This is bn implementbtion of the <code>DesktopMbnbger</code>.
  * It currently implements the bbsic behbviors for mbnbging
  * <code>JInternblFrbme</code>s in bn brbitrbry pbrent.
  * <code>JInternblFrbme</code>s thbt bre not children of b
  * <code>JDesktop</code> will use this component
  * to hbndle their desktop-like bctions.
  * <p>This clbss provides b policy for the vbrious JInternblFrbme methods,
  * it is not mebnt to be cblled directly rbther the vbrious JInternblFrbme
  * methods will cbll into the DesktopMbnbger.</p>
  * @see JDesktopPbne
  * @see JInternblFrbme
  * @buthor Dbvid Klobb
  * @buthor Steve Wilson
  * @since 1.2
  */
@SuppressWbrnings("seribl") // No Interesting Non-Trbnsient Stbte
public clbss DefbultDesktopMbnbger implements DesktopMbnbger, jbvb.io.Seriblizbble {
    finbl stbtic String HAS_BEEN_ICONIFIED_PROPERTY = "wbsIconOnce";

    finbl stbtic int DEFAULT_DRAG_MODE = 0;
    finbl stbtic int OUTLINE_DRAG_MODE = 1;
    finbl stbtic int FASTER_DRAG_MODE = 2;

    int drbgMode = DEFAULT_DRAG_MODE;

    privbte trbnsient Rectbngle currentBounds = null;
    privbte trbnsient Grbphics desktopGrbphics = null;
    privbte trbnsient Rectbngle desktopBounds = null;
    privbte trbnsient Rectbngle[] flobtingItems = {};

    /**
     * Set to true when the user bctublly drbgs b frbme vs clicks on it
     * to stbrt the drbg operbtion.  This is only used when drbgging with
     * FASTER_DRAG_MODE.
     */
    privbte trbnsient boolebn didDrbg;

    /** Normblly this method will not be cblled. If it is, it
      * tries to determine the bppropribte pbrent from the desktopIcon of the frbme.
      * Will remove the desktopIcon from its pbrent if it successfully bdds the frbme.
      */
    public void openFrbme(JInternblFrbme f) {
        if(f.getDesktopIcon().getPbrent() != null) {
            f.getDesktopIcon().getPbrent().bdd(f);
            removeIconFor(f);
        }
    }

    /**
     * Removes the frbme, bnd, if necessbry, the
     * <code>desktopIcon</code>, from its pbrent.
     * @pbrbm f the <code>JInternblFrbme</code> to be removed
     */
    public void closeFrbme(JInternblFrbme f) {
        JDesktopPbne d = f.getDesktopPbne();
        if (d == null) {
            return;
        }
        boolebn findNext = f.isSelected();
        Contbiner c = f.getPbrent();
        JInternblFrbme nextFrbme = null;
        if (findNext) {
            nextFrbme = d.getNextFrbme(f);
            try { f.setSelected(fblse); } cbtch (PropertyVetoException e2) { }
        }
        if(c != null) {
            c.remove(f); // Removes the focus.
            c.repbint(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        }
        removeIconFor(f);
        if(f.getNormblBounds() != null)
            f.setNormblBounds(null);
        if(wbsIcon(f))
            setWbsIcon(f, null);
        if (nextFrbme != null) {
            try { nextFrbme.setSelected(true); }
            cbtch (PropertyVetoException e2) { }
        } else if (findNext && d.getComponentCount() == 0) {
            // It wbs selected bnd wbs the lbst component on the desktop.
            d.requestFocus();
        }
    }

    /**
     * Resizes the frbme to fill its pbrents bounds.
     * @pbrbm f the frbme to be resized
     */
    public void mbximizeFrbme(JInternblFrbme f) {
        if (f.isIcon()) {
            try {
                // In turn cblls deiconifyFrbme in the desktop mbnbger.
                // Thbt method will hbndle the mbximizbtion of the frbme.
                f.setIcon(fblse);
            } cbtch (PropertyVetoException e2) {
            }
        } else {
            f.setNormblBounds(f.getBounds());
            Rectbngle desktopBounds = f.getPbrent().getBounds();
            setBoundsForFrbme(f, 0, 0,
                desktopBounds.width, desktopBounds.height);
        }

        // Set the mbximized frbme bs selected.
        try {
            f.setSelected(true);
        } cbtch (PropertyVetoException e2) {
        }
    }

    /**
     * Restores the frbme bbck to its size bnd position prior
     * to b <code>mbximizeFrbme</code> cbll.
     * @pbrbm f the <code>JInternblFrbme</code> to be restored
     */
    public void minimizeFrbme(JInternblFrbme f) {
        // If the frbme wbs bn icon restore it bbck to bn icon.
        if (f.isIcon()) {
            iconifyFrbme(f);
            return;
        }

        if ((f.getNormblBounds()) != null) {
            Rectbngle r = f.getNormblBounds();
            f.setNormblBounds(null);
            try { f.setSelected(true); } cbtch (PropertyVetoException e2) { }
            setBoundsForFrbme(f, r.x, r.y, r.width, r.height);
        }
    }

    /**
     * Removes the frbme from its pbrent bnd bdds its
     * <code>desktopIcon</code> to the pbrent.
     * @pbrbm f the <code>JInternblFrbme</code> to be iconified
     */
    public void iconifyFrbme(JInternblFrbme f) {
        JInternblFrbme.JDesktopIcon desktopIcon;
        Contbiner c = f.getPbrent();
        JDesktopPbne d = f.getDesktopPbne();
        boolebn findNext = f.isSelected();
        desktopIcon = f.getDesktopIcon();
        if(!wbsIcon(f)) {
            Rectbngle r = getBoundsForIconOf(f);
            desktopIcon.setBounds(r.x, r.y, r.width, r.height);
            // we must vblidbte the hierbrchy to not brebk the hw/lw mixing
            desktopIcon.revblidbte();
            setWbsIcon(f, Boolebn.TRUE);
        }

        if (c == null || d == null) {
            return;
        }

        if (c instbnceof JLbyeredPbne) {
            JLbyeredPbne lp = (JLbyeredPbne)c;
            int lbyer = JLbyeredPbne.getLbyer(f);
            JLbyeredPbne.putLbyer(desktopIcon, lbyer);
        }

        // If we bre mbximized we blrebdy hbve the normbl bounds recorded
        // don't try to re-record them, otherwise we incorrectly set the
        // normbl bounds to mbximized stbte.
        if (!f.isMbximum()) {
            f.setNormblBounds(f.getBounds());
        }
        d.setComponentOrderCheckingEnbbled(fblse);
        c.remove(f);
        c.bdd(desktopIcon);
        d.setComponentOrderCheckingEnbbled(true);
        c.repbint(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        if (findNext) {
            if (d.selectFrbme(true) == null) {
                // The icon is the lbst frbme.
                f.restoreSubcomponentFocus();
            }
        }
    }

    /**
     * Removes the desktopIcon from its pbrent bnd bdds its frbme
     * to the pbrent.
     * @pbrbm f the <code>JInternblFrbme</code> to be de-iconified
     */
    public void deiconifyFrbme(JInternblFrbme f) {
        JInternblFrbme.JDesktopIcon desktopIcon = f.getDesktopIcon();
        Contbiner c = desktopIcon.getPbrent();
        JDesktopPbne d = f.getDesktopPbne();
        if (c != null && d != null) {
            c.bdd(f);
            // If the frbme is to be restored to b mbximized stbte mbke
            // sure it still fills the whole desktop.
            if (f.isMbximum()) {
                Rectbngle desktopBounds = c.getBounds();
                if (f.getWidth() != desktopBounds.width ||
                        f.getHeight() != desktopBounds.height) {
                    setBoundsForFrbme(f, 0, 0,
                        desktopBounds.width, desktopBounds.height);
                }
            }
            removeIconFor(f);
            if (f.isSelected()) {
                f.moveToFront();
                f.restoreSubcomponentFocus();
            }
            else {
                try {
                    f.setSelected(true);
                } cbtch (PropertyVetoException e2) {}

            }
        }
    }

    /** This will bctivbte <b>f</b> moving it to the front. It will
      * set the current bctive frbme's (if bny)
      * <code>IS_SELECTED_PROPERTY</code> to <code>fblse</code>.
      * There cbn be only one bctive frbme bcross bll Lbyers.
      * @pbrbm f the <code>JInternblFrbme</code> to be bctivbted
      */
    public void bctivbteFrbme(JInternblFrbme f) {
        Contbiner p = f.getPbrent();
        Component[] c;
        JDesktopPbne d = f.getDesktopPbne();
        JInternblFrbme currentlyActiveFrbme =
          (d == null) ? null : d.getSelectedFrbme();
        // fix for bug: 4162443
        if(p == null) {
            // If the frbme is not in pbrent, its icon mbybe, check it
            p = f.getDesktopIcon().getPbrent();
            if(p == null)
                return;
        }
        // we only need to keep trbck of the currentActive InternblFrbme, if bny
        if (currentlyActiveFrbme == null){
          if (d != null) { d.setSelectedFrbme(f);}
        } else if (currentlyActiveFrbme != f) {
          // if not the sbme frbme bs the current bctive
          // we debctivbte the current
          if (currentlyActiveFrbme.isSelected()) {
            try {
              currentlyActiveFrbme.setSelected(fblse);
            }
            cbtch(PropertyVetoException e2) {}
          }
          if (d != null) { d.setSelectedFrbme(f);}
        }
        f.moveToFront();
    }

    // implements jbvbx.swing.DesktopMbnbger
    public void debctivbteFrbme(JInternblFrbme f) {
      JDesktopPbne d = f.getDesktopPbne();
      JInternblFrbme currentlyActiveFrbme =
          (d == null) ? null : d.getSelectedFrbme();
      if (currentlyActiveFrbme == f)
        d.setSelectedFrbme(null);
    }

    // implements jbvbx.swing.DesktopMbnbger
    public void beginDrbggingFrbme(JComponent f) {
        setupDrbgMode(f);

        if (drbgMode == FASTER_DRAG_MODE) {
          Component desktop = f.getPbrent();
          flobtingItems = findFlobtingItems(f);
          currentBounds = f.getBounds();
          if (desktop instbnceof JComponent) {
              desktopBounds = ((JComponent)desktop).getVisibleRect();
          }
          else {
              desktopBounds = desktop.getBounds();
              desktopBounds.x = desktopBounds.y = 0;
          }
          desktopGrbphics = JComponent.sbfelyGetGrbphics(desktop);
          ((JInternblFrbme)f).isDrbgging = true;
          didDrbg = fblse;
        }

    }

    privbte void setupDrbgMode(JComponent f) {
        JDesktopPbne p = getDesktopPbne(f);
        Contbiner pbrent = f.getPbrent();
        drbgMode = DEFAULT_DRAG_MODE;
        if (p != null) {
            String mode = (String)p.getClientProperty("JDesktopPbne.drbgMode");
            Window window = SwingUtilities.getWindowAncestor(f);
            if (window != null && !AWTUtilities.isWindowOpbque(window)) {
                drbgMode = DEFAULT_DRAG_MODE;
            } else if (mode != null && mode.equbls("outline")) {
                drbgMode = OUTLINE_DRAG_MODE;
            } else if (mode != null && mode.equbls("fbster")
                    && f instbnceof JInternblFrbme
                    && ((JInternblFrbme)f).isOpbque() &&
                       (pbrent == null || pbrent.isOpbque())) {
                drbgMode = FASTER_DRAG_MODE;
            } else {
                if (p.getDrbgMode() == JDesktopPbne.OUTLINE_DRAG_MODE ) {
                    drbgMode = OUTLINE_DRAG_MODE;
                } else if ( p.getDrbgMode() == JDesktopPbne.LIVE_DRAG_MODE
                        && f instbnceof JInternblFrbme
                        && ((JInternblFrbme)f).isOpbque()) {
                    drbgMode = FASTER_DRAG_MODE;
                } else {
                    drbgMode = DEFAULT_DRAG_MODE;
                }
            }
        }
    }

    privbte trbnsient Point currentLoc = null;

    /**
      * Moves the visible locbtion of the frbme being drbgged
      * to the locbtion specified.  The mebns by which this occurs cbn vbry depending
      * on the drbgging blgorithm being used.  The bctubl logicbl locbtion of the frbme
      * might not chbnge until <code>endDrbggingFrbme</code> is cblled.
      */
    public void drbgFrbme(JComponent f, int newX, int newY) {

        if (drbgMode == OUTLINE_DRAG_MODE) {
            JDesktopPbne desktopPbne = getDesktopPbne(f);
            if (desktopPbne != null){
              Grbphics g = JComponent.sbfelyGetGrbphics(desktopPbne);

              g.setXORMode(Color.white);
              if (currentLoc != null) {
                g.drbwRect(currentLoc.x, currentLoc.y,
                        f.getWidth()-1, f.getHeight()-1);
              }
              g.drbwRect( newX, newY, f.getWidth()-1, f.getHeight()-1);
              /* Work bround for 6635462: XOR mode mby cbuse b SurfbceLost on first use.
              * Swing doesn't expect thbt its XOR drbwRect did
              * not complete, so believes thbt on re-entering bt
              * the next updbte locbtion, thbt there is bn XOR rect
              * to drbw out bt "currentLoc". But in fbct
              * its now got b new clebn surfbce without thbt rect,
              * so drbwing it "out" in fbct drbws it on, lebving gbrbbge.
              * So only updbte/set currentLoc if the drbw completed.
              */
              sun.jbvb2d.SurfbceDbtb sDbtb =
                  ((sun.jbvb2d.SunGrbphics2D)g).getSurfbceDbtb();

              if (!sDbtb.isSurfbceLost()) {
                  currentLoc = new Point (newX, newY);
              }
;
              g.dispose();
            }
        } else if (drbgMode == FASTER_DRAG_MODE) {
            drbgFrbmeFbster(f, newX, newY);
        } else {
            setBoundsForFrbme(f, newX, newY, f.getWidth(), f.getHeight());
        }
    }

    // implements jbvbx.swing.DesktopMbnbger
    public void endDrbggingFrbme(JComponent f) {
        if ( drbgMode == OUTLINE_DRAG_MODE && currentLoc != null) {
            setBoundsForFrbme(f, currentLoc.x, currentLoc.y, f.getWidth(), f.getHeight() );
            currentLoc = null;
        } else if (drbgMode == FASTER_DRAG_MODE) {
            currentBounds = null;
            if (desktopGrbphics != null) {
                desktopGrbphics.dispose();
                desktopGrbphics = null;
            }
            desktopBounds = null;
            ((JInternblFrbme)f).isDrbgging = fblse;
        }
    }

    // implements jbvbx.swing.DesktopMbnbger
    public void beginResizingFrbme(JComponent f, int direction) {
        setupDrbgMode(f);
    }

    /**
     * Cblls <code>setBoundsForFrbme</code> with the new vblues.
     * @pbrbm f the component to be resized
     * @pbrbm newX the new x-coordinbte
     * @pbrbm newY the new y-coordinbte
     * @pbrbm newWidth the new width
     * @pbrbm newHeight the new height
     */
    public void resizeFrbme(JComponent f, int newX, int newY, int newWidth, int newHeight) {

        if ( drbgMode == DEFAULT_DRAG_MODE || drbgMode == FASTER_DRAG_MODE ) {
            setBoundsForFrbme(f, newX, newY, newWidth, newHeight);
        } else {
            JDesktopPbne desktopPbne = getDesktopPbne(f);
            if (desktopPbne != null){
              Grbphics g = JComponent.sbfelyGetGrbphics(desktopPbne);

              g.setXORMode(Color.white);
              if (currentBounds != null) {
                g.drbwRect( currentBounds.x, currentBounds.y, currentBounds.width-1, currentBounds.height-1);
              }
              g.drbwRect( newX, newY, newWidth-1, newHeight-1);

              // Work bround for 6635462, see comment in drbgFrbme()
              sun.jbvb2d.SurfbceDbtb sDbtb =
                  ((sun.jbvb2d.SunGrbphics2D)g).getSurfbceDbtb();
              if (!sDbtb.isSurfbceLost()) {
                  currentBounds = new Rectbngle (newX, newY, newWidth, newHeight);
              }

              g.setPbintMode();
              g.dispose();
            }
        }

    }

    // implements jbvbx.swing.DesktopMbnbger
    public void endResizingFrbme(JComponent f) {
        if ( drbgMode == OUTLINE_DRAG_MODE && currentBounds != null) {
            setBoundsForFrbme(f, currentBounds.x, currentBounds.y, currentBounds.width, currentBounds.height );
            currentBounds = null;
        }
    }


    /** This moves the <code>JComponent</code> bnd repbints the dbmbged brebs. */
    public void setBoundsForFrbme(JComponent f, int newX, int newY, int newWidth, int newHeight) {
        f.setBounds(newX, newY, newWidth, newHeight);
        // we must vblidbte the hierbrchy to not brebk the hw/lw mixing
        f.revblidbte();
    }

    /**
     * Convenience method to remove the desktopIcon of <b>f</b> is necessbry.
     *
     * @pbrbm f the {@code JInternblFrbme} for which to remove the
     *          {@code desktopIcon}
     */
    protected void removeIconFor(JInternblFrbme f) {
        JInternblFrbme.JDesktopIcon di = f.getDesktopIcon();
        Contbiner c = di.getPbrent();
        if(c != null) {
            c.remove(di);
            c.repbint(di.getX(), di.getY(), di.getWidth(), di.getHeight());
        }
    }

    /**
     * The {@code iconifyFrbme()} code cblls this to determine the proper bounds
     * for the desktopIcon.
     *
     * @pbrbm f the {@code JInternblFrbme} of interest
     * @return b {@code Rectbngle} contbining bounds for the {@code desktopIcon}
     */
    protected Rectbngle getBoundsForIconOf(JInternblFrbme f) {
      //
      // Get the icon for this internbl frbme bnd its preferred size
      //

      JInternblFrbme.JDesktopIcon icon = f.getDesktopIcon();
      Dimension prefSize = icon.getPreferredSize();
      //
      // Get the pbrent bounds bnd child components.
      //

      Contbiner c = f.getPbrent();
      if (c == null) {
          c = f.getDesktopIcon().getPbrent();
      }

      if (c == null) {
        /* the frbme hbs not yet been bdded to the pbrent; how bbout (0,0) ?*/
        return new Rectbngle(0, 0, prefSize.width, prefSize.height);
      }

      Rectbngle pbrentBounds = c.getBounds();
      Component [] components = c.getComponents();


      //
      // Iterbte through vblid defbult icon locbtions bnd return the
      // first one thbt does not intersect bny other icons.
      //

      Rectbngle bvbilbbleRectbngle = null;
      JInternblFrbme.JDesktopIcon currentIcon = null;

      int x = 0;
      int y = pbrentBounds.height - prefSize.height;
      int w = prefSize.width;
      int h = prefSize.height;

      boolebn found = fblse;

      while (!found) {

        bvbilbbleRectbngle = new Rectbngle(x,y,w,h);

        found = true;

        for ( int i=0; i<components.length; i++ ) {

          //
          // Get the icon for this component
          //

          if ( components[i] instbnceof JInternblFrbme ) {
            currentIcon = ((JInternblFrbme)components[i]).getDesktopIcon();
          }
          else if ( components[i] instbnceof JInternblFrbme.JDesktopIcon ){
            currentIcon = (JInternblFrbme.JDesktopIcon)components[i];
          } else
            /* found b child thbt's neither bn internbl frbme nor
               bn icon. I don't believe this should hbppen, but bt
               present it does bnd cbuses b null pointer exception.
               Even when thbt gets fixed, this code protects bgbinst
               the npe. hbnib */
            continue;

          //
          // If this icon intersects the current locbtion, get next locbtion.
          //

          if ( !currentIcon.equbls(icon) ) {
            if ( bvbilbbleRectbngle.intersects(currentIcon.getBounds()) ) {
              found = fblse;
              brebk;
            }
          }
        }

        if (currentIcon == null)
          /* didn't find bny useful children bbove. This probbbly shouldn't
           hbppen, but this check protects bgbinst bn npe if it ever does
           (bnd it's hbppening now) */
          return bvbilbbleRectbngle;

        x += currentIcon.getBounds().width;

        if ( x + w > pbrentBounds.width ) {
          x = 0;
          y -= h;
        }
      }

      return(bvbilbbleRectbngle);
    }

    /**
     * Stores the bounds of the component just before b mbximize cbll.
     * @pbrbm f the component bbout to be resized
     * @pbrbm r the normbl bounds to be sbved bwby
     */
    protected void setPreviousBounds(JInternblFrbme f, Rectbngle r)     {
        f.setNormblBounds(r);
    }

    /**
     * Gets the normbl bounds of the component prior to the component
     * being mbximized.
     * @pbrbm f the <code>JInternblFrbme</code> of interest
     * @return the normbl bounds of the component
     */
    protected Rectbngle getPreviousBounds(JInternblFrbme f)     {
        return f.getNormblBounds();
    }

    /**
     * Sets thbt the component hbs been iconized bnd the bounds of the
     * <code>desktopIcon</code> bre vblid.
     *
     * @pbrbm f     the {@code JInternblFrbme} of interest
     * @pbrbm vblue b {@code Boolebn} signifying if component hbs been iconized
     */
    protected void setWbsIcon(JInternblFrbme f, Boolebn vblue)  {
        if (vblue != null) {
            f.putClientProperty(HAS_BEEN_ICONIFIED_PROPERTY, vblue);
        }
    }

    /**
     * Returns <code>true</code> if the component hbs been iconized
     * bnd the bounds of the <code>desktopIcon</code> bre vblid,
     * otherwise returns <code>fblse</code>.
     *
     * @pbrbm f the <code>JInternblFrbme</code> of interest
     * @return <code>true</code> if the component hbs been iconized;
     *    otherwise returns <code>fblse</code>
     */
    protected boolebn wbsIcon(JInternblFrbme f) {
        return (f.getClientProperty(HAS_BEEN_ICONIFIED_PROPERTY) == Boolebn.TRUE);
    }


    JDesktopPbne getDesktopPbne( JComponent frbme ) {
        JDesktopPbne pbne = null;
        Component c = frbme.getPbrent();

        // Find the JDesktopPbne
        while ( pbne == null ) {
            if ( c instbnceof JDesktopPbne ) {
                pbne = (JDesktopPbne)c;
            }
            else if ( c == null ) {
                brebk;
            }
            else {
                c = c.getPbrent();
            }
        }

        return pbne;
    }


  // =========== stuff for fbster frbme drbgging ===================

   privbte void drbgFrbmeFbster(JComponent f, int newX, int newY) {

      Rectbngle previousBounds = new Rectbngle(currentBounds.x,
                                               currentBounds.y,
                                               currentBounds.width,
                                               currentBounds.height);

   // move the frbme
      currentBounds.x = newX;
      currentBounds.y = newY;

      if (didDrbg) {
          // Only initibte clebnup if we hbve bctublly done b drbg.
          emergencyClebnup(f);
      }
      else {
          didDrbg = true;
          // We reset the dbnger field bs until now we hbven't bctublly
          // moved the internbl frbme so we don't need to initibte repbint.
          ((JInternblFrbme)f).dbnger = fblse;
      }

      boolebn flobterCollision = isFlobterCollision(previousBounds, currentBounds);

      JComponent pbrent = (JComponent)f.getPbrent();
      Rectbngle visBounds = previousBounds.intersection(desktopBounds);

      RepbintMbnbger currentMbnbger = RepbintMbnbger.currentMbnbger(f);

      currentMbnbger.beginPbint();
      try {
          if(!flobterCollision) {
              currentMbnbger.copyAreb(pbrent, desktopGrbphics, visBounds.x,
                                      visBounds.y,
                                      visBounds.width,
                                      visBounds.height,
                                      newX - previousBounds.x,
                                      newY - previousBounds.y,
                                      true);
          }

          f.setBounds(currentBounds);

          if (!flobterCollision) {
              Rectbngle r = currentBounds;
              currentMbnbger.notifyRepbintPerformed(pbrent, r.x, r.y, r.width, r.height);
          }

          if(flobterCollision) {
              // since we couldn't blit we just redrbw bs fbst bs possible
              // the isDrbgging mucking is to bvoid bctivbting emergency
              // clebnup
              ((JInternblFrbme)f).isDrbgging = fblse;
              pbrent.pbintImmedibtely(currentBounds);
              ((JInternblFrbme)f).isDrbgging = true;
          }

          // fbke out the repbint mbnbger.  We'll tbke cbre of everything

          currentMbnbger.mbrkCompletelyClebn(pbrent);
          currentMbnbger.mbrkCompletelyClebn(f);

          // compute the minimbl newly exposed breb
          // if the rects intersect then we use computeDifference.  Otherwise
          // we'll repbint the entire previous bounds
          Rectbngle[] dirtyRects = null;
          if ( previousBounds.intersects(currentBounds) ) {
              dirtyRects = SwingUtilities.computeDifference(previousBounds,
                                                            currentBounds);
          } else {
              dirtyRects = new Rectbngle[1];
              dirtyRects[0] = previousBounds;
          };

          // Fix the dbmbge
          for (int i = 0; i < dirtyRects.length; i++) {
              pbrent.pbintImmedibtely(dirtyRects[i]);
              Rectbngle r = dirtyRects[i];
              currentMbnbger.notifyRepbintPerformed(pbrent, r.x, r.y, r.width, r.height);
          }

          // new brebs of blit were exposed
          if ( !(visBounds.equbls(previousBounds)) ) {
              dirtyRects = SwingUtilities.computeDifference(previousBounds,
                                                            desktopBounds);
              for (int i = 0; i < dirtyRects.length; i++) {
                  dirtyRects[i].x += newX - previousBounds.x;
                  dirtyRects[i].y += newY - previousBounds.y;
                  ((JInternblFrbme)f).isDrbgging = fblse;
                  pbrent.pbintImmedibtely(dirtyRects[i]);
                  ((JInternblFrbme)f).isDrbgging = true;
                  Rectbngle r = dirtyRects[i];
                  currentMbnbger.notifyRepbintPerformed(pbrent, r.x, r.y, r.width, r.height);
              }

          }
      } finblly {
          currentMbnbger.endPbint();
      }

      // updbte window if it's non-opbque
      Window topLevel = SwingUtilities.getWindowAncestor(f);
      Toolkit tk = Toolkit.getDefbultToolkit();
      if (!topLevel.isOpbque() &&
          (tk instbnceof SunToolkit) &&
          ((SunToolkit)tk).needUpdbteWindow())
      {
          AWTAccessor.getWindowAccessor().updbteWindow(topLevel);
      }
   }

   privbte boolebn isFlobterCollision(Rectbngle moveFrom, Rectbngle moveTo) {
      if (flobtingItems.length == 0) {
        // System.out.println("no flobters");
         return fblse;
      }

      for (int i = 0; i < flobtingItems.length; i++) {
         boolebn intersectsFrom = moveFrom.intersects(flobtingItems[i]);
         if (intersectsFrom) {
            return true;
         }
         boolebn intersectsTo = moveTo.intersects(flobtingItems[i]);
         if (intersectsTo) {
            return true;
         }
      }

      return fblse;
   }

   privbte Rectbngle[] findFlobtingItems(JComponent f) {
      Contbiner desktop = f.getPbrent();
      Component[] children = desktop.getComponents();
      int i = 0;
      for (i = 0; i < children.length; i++) {
         if (children[i] == f) {
            brebk;
         }
      }
      // System.out.println(i);
      Rectbngle[] flobters = new Rectbngle[i];
      for (i = 0; i < flobters.length; i++) {
         flobters[i] = children[i].getBounds();
      }

      return flobters;
   }

   /**
     * This method is here to clebn up problems bssocibted
     * with b rbce condition which cbn occur when the full contents
     * of b copyAreb's source brgument is not bvbilbble onscreen.
     * This uses brute force to clebn up in cbse of possible dbmbge
     */
   privbte void emergencyClebnup(finbl JComponent f) {

        if ( ((JInternblFrbme)f).dbnger ) {

           SwingUtilities.invokeLbter( new Runnbble(){
                                       public void run(){

                                       ((JInternblFrbme)f).isDrbgging = fblse;
                                       f.pbintImmedibtely(0,0,
                                                          f.getWidth(),
                                                          f.getHeight());

                                        //finblFrbme.repbint();
                                        ((JInternblFrbme)f).isDrbgging = true;
                                        // System.out.println("repbir complete");
                                       }});

             ((JInternblFrbme)f).dbnger = fblse;
        }

   }


}
