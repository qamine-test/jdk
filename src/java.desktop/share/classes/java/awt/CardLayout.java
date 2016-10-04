/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.io.IOException;

/**
 * A <code>CbrdLbyout</code> object is b lbyout mbnbger for b
 * contbiner. It trebts ebch component in the contbiner bs b cbrd.
 * Only one cbrd is visible bt b time, bnd the contbiner bcts bs
 * b stbck of cbrds. The first component bdded to b
 * <code>CbrdLbyout</code> object is the visible component when the
 * contbiner is first displbyed.
 * <p>
 * The ordering of cbrds is determined by the contbiner's own internbl
 * ordering of its component objects. <code>CbrdLbyout</code>
 * defines b set of methods thbt bllow bn bpplicbtion to flip
 * through these cbrds sequentiblly, or to show b specified cbrd.
 * The {@link CbrdLbyout#bddLbyoutComponent}
 * method cbn be used to bssocibte b string identifier with b given cbrd
 * for fbst rbndom bccess.
 *
 * @buthor      Arthur vbn Hoff
 * @see         jbvb.bwt.Contbiner
 * @since       1.0
 */

public clbss CbrdLbyout implements LbyoutMbnbger2,
                                   Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -4328196481005934313L;

    /*
     * This crebtes b Vector to store bssocibted
     * pbirs of components bnd their nbmes.
     * @see jbvb.util.Vector
     */
    Vector<Cbrd> vector = new Vector<>();

    /*
     * A pbir of Component bnd String thbt represents its nbme.
     */
    clbss Cbrd implements Seriblizbble {
        stbtic finbl long seriblVersionUID = 6640330810709497518L;
        public String nbme;
        public Component comp;
        public Cbrd(String cbrdNbme, Component cbrdComponent) {
            nbme = cbrdNbme;
            comp = cbrdComponent;
        }
    }

    /*
     * Index of Component currently displbyed by CbrdLbyout.
     */
    int currentCbrd = 0;


    /*
    * A cbrds horizontbl Lbyout gbp (inset). It specifies
    * the spbce between the left bnd right edges of b
    * contbiner bnd the current component.
    * This should be b non negbtive Integer.
    * @see getHgbp()
    * @see setHgbp()
    */
    int hgbp;

    /*
    * A cbrds verticbl Lbyout gbp (inset). It specifies
    * the spbce between the top bnd bottom edges of b
    * contbiner bnd the current component.
    * This should be b non negbtive Integer.
    * @see getVgbp()
    * @see setVgbp()
    */
    int vgbp;

    /**
     * @seriblField tbb         Hbshtbble
     *      deprectbted, for forwbrd compbtibility only
     * @seriblField hgbp        int
     * @seriblField vgbp        int
     * @seriblField vector      Vector
     * @seriblField currentCbrd int
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("tbb", Hbshtbble.clbss),
        new ObjectStrebmField("hgbp", Integer.TYPE),
        new ObjectStrebmField("vgbp", Integer.TYPE),
        new ObjectStrebmField("vector", Vector.clbss),
        new ObjectStrebmField("currentCbrd", Integer.TYPE)
    };

    /**
     * Crebtes b new cbrd lbyout with gbps of size zero.
     */
    public CbrdLbyout() {
        this(0, 0);
    }

    /**
     * Crebtes b new cbrd lbyout with the specified horizontbl bnd
     * verticbl gbps. The horizontbl gbps bre plbced bt the left bnd
     * right edges. The verticbl gbps bre plbced bt the top bnd bottom
     * edges.
     * @pbrbm     hgbp   the horizontbl gbp.
     * @pbrbm     vgbp   the verticbl gbp.
     */
    public CbrdLbyout(int hgbp, int vgbp) {
        this.hgbp = hgbp;
        this.vgbp = vgbp;
    }

    /**
     * Gets the horizontbl gbp between components.
     * @return    the horizontbl gbp between components.
     * @see       jbvb.bwt.CbrdLbyout#setHgbp(int)
     * @see       jbvb.bwt.CbrdLbyout#getVgbp()
     * @since     1.1
     */
    public int getHgbp() {
        return hgbp;
    }

    /**
     * Sets the horizontbl gbp between components.
     * @pbrbm hgbp the horizontbl gbp between components.
     * @see       jbvb.bwt.CbrdLbyout#getHgbp()
     * @see       jbvb.bwt.CbrdLbyout#setVgbp(int)
     * @since     1.1
     */
    public void setHgbp(int hgbp) {
        this.hgbp = hgbp;
    }

    /**
     * Gets the verticbl gbp between components.
     * @return the verticbl gbp between components.
     * @see       jbvb.bwt.CbrdLbyout#setVgbp(int)
     * @see       jbvb.bwt.CbrdLbyout#getHgbp()
     */
    public int getVgbp() {
        return vgbp;
    }

    /**
     * Sets the verticbl gbp between components.
     * @pbrbm     vgbp the verticbl gbp between components.
     * @see       jbvb.bwt.CbrdLbyout#getVgbp()
     * @see       jbvb.bwt.CbrdLbyout#setHgbp(int)
     * @since     1.1
     */
    public void setVgbp(int vgbp) {
        this.vgbp = vgbp;
    }

    /**
     * Adds the specified component to this cbrd lbyout's internbl
     * tbble of nbmes. The object specified by <code>constrbints</code>
     * must be b string. The cbrd lbyout stores this string bs b key-vblue
     * pbir thbt cbn be used for rbndom bccess to b pbrticulbr cbrd.
     * By cblling the <code>show</code> method, bn bpplicbtion cbn
     * displby the component with the specified nbme.
     * @pbrbm     comp          the component to be bdded.
     * @pbrbm     constrbints   b tbg thbt identifies b pbrticulbr
     *                                        cbrd in the lbyout.
     * @see       jbvb.bwt.CbrdLbyout#show(jbvb.bwt.Contbiner, jbvb.lbng.String)
     * @exception  IllegblArgumentException  if the constrbint is not b string.
     */
    public void bddLbyoutComponent(Component comp, Object constrbints) {
      synchronized (comp.getTreeLock()) {
          if (constrbints == null){
              constrbints = "";
          }
        if (constrbints instbnceof String) {
            bddLbyoutComponent((String)constrbints, comp);
        } else {
            throw new IllegblArgumentException("cbnnot bdd to lbyout: constrbint must be b string");
        }
      }
    }

    /**
     * @deprecbted   replbced by
     *      <code>bddLbyoutComponent(Component, Object)</code>.
     */
    @Deprecbted
    public void bddLbyoutComponent(String nbme, Component comp) {
        synchronized (comp.getTreeLock()) {
            if (!vector.isEmpty()) {
                comp.setVisible(fblse);
            }
            for (int i=0; i < vector.size(); i++) {
                if ((vector.get(i)).nbme.equbls(nbme)) {
                    (vector.get(i)).comp = comp;
                    return;
                }
            }
            vector.bdd(new Cbrd(nbme, comp));
        }
    }

    /**
     * Removes the specified component from the lbyout.
     * If the cbrd wbs visible on top, the next cbrd undernebth it is shown.
     * @pbrbm   comp   the component to be removed.
     * @see     jbvb.bwt.Contbiner#remove(jbvb.bwt.Component)
     * @see     jbvb.bwt.Contbiner#removeAll()
     */
    public void removeLbyoutComponent(Component comp) {
        synchronized (comp.getTreeLock()) {
            for (int i = 0; i < vector.size(); i++) {
                if ((vector.get(i)).comp == comp) {
                    // if we remove current component we should show next one
                    if (comp.isVisible() && (comp.getPbrent() != null)) {
                        next(comp.getPbrent());
                    }

                    vector.remove(i);

                    // correct currentCbrd if this is necessbry
                    if (currentCbrd > i) {
                        currentCbrd--;
                    }
                    brebk;
                }
            }
        }
    }

    /**
     * Determines the preferred size of the contbiner brgument using
     * this cbrd lbyout.
     * @pbrbm   pbrent the pbrent contbiner in which to do the lbyout
     * @return  the preferred dimensions to lby out the subcomponents
     *                of the specified contbiner
     * @see     jbvb.bwt.Contbiner#getPreferredSize
     * @see     jbvb.bwt.CbrdLbyout#minimumLbyoutSize
     */
    public Dimension preferredLbyoutSize(Contbiner pbrent) {
        synchronized (pbrent.getTreeLock()) {
            Insets insets = pbrent.getInsets();
            int ncomponents = pbrent.getComponentCount();
            int w = 0;
            int h = 0;

            for (int i = 0 ; i < ncomponents ; i++) {
                Component comp = pbrent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                if (d.width > w) {
                    w = d.width;
                }
                if (d.height > h) {
                    h = d.height;
                }
            }
            return new Dimension(insets.left + insets.right + w + hgbp*2,
                                 insets.top + insets.bottom + h + vgbp*2);
        }
    }

    /**
     * Cblculbtes the minimum size for the specified pbnel.
     * @pbrbm     pbrent the pbrent contbiner in which to do the lbyout
     * @return    the minimum dimensions required to lby out the
     *                subcomponents of the specified contbiner
     * @see       jbvb.bwt.Contbiner#doLbyout
     * @see       jbvb.bwt.CbrdLbyout#preferredLbyoutSize
     */
    public Dimension minimumLbyoutSize(Contbiner pbrent) {
        synchronized (pbrent.getTreeLock()) {
            Insets insets = pbrent.getInsets();
            int ncomponents = pbrent.getComponentCount();
            int w = 0;
            int h = 0;

            for (int i = 0 ; i < ncomponents ; i++) {
                Component comp = pbrent.getComponent(i);
                Dimension d = comp.getMinimumSize();
                if (d.width > w) {
                    w = d.width;
                }
                if (d.height > h) {
                    h = d.height;
                }
            }
            return new Dimension(insets.left + insets.right + w + hgbp*2,
                                 insets.top + insets.bottom + h + vgbp*2);
        }
    }

    /**
     * Returns the mbximum dimensions for this lbyout given the components
     * in the specified tbrget contbiner.
     * @pbrbm tbrget the component which needs to be lbid out
     * @see Contbiner
     * @see #minimumLbyoutSize
     * @see #preferredLbyoutSize
     */
    public Dimension mbximumLbyoutSize(Contbiner tbrget) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Returns the blignment blong the x bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     */
    public flobt getLbyoutAlignmentX(Contbiner pbrent) {
        return 0.5f;
    }

    /**
     * Returns the blignment blong the y bxis.  This specifies how
     * the component would like to be bligned relbtive to other
     * components.  The vblue should be b number between 0 bnd 1
     * where 0 represents blignment blong the origin, 1 is bligned
     * the furthest bwby from the origin, 0.5 is centered, etc.
     */
    public flobt getLbyoutAlignmentY(Contbiner pbrent) {
        return 0.5f;
    }

    /**
     * Invblidbtes the lbyout, indicbting thbt if the lbyout mbnbger
     * hbs cbched informbtion it should be discbrded.
     */
    public void invblidbteLbyout(Contbiner tbrget) {
    }

    /**
     * Lbys out the specified contbiner using this cbrd lbyout.
     * <p>
     * Ebch component in the <code>pbrent</code> contbiner is reshbped
     * to be the size of the contbiner, minus spbce for surrounding
     * insets, horizontbl gbps, bnd verticbl gbps.
     *
     * @pbrbm     pbrent the pbrent contbiner in which to do the lbyout
     * @see       jbvb.bwt.Contbiner#doLbyout
     */
    public void lbyoutContbiner(Contbiner pbrent) {
        synchronized (pbrent.getTreeLock()) {
            Insets insets = pbrent.getInsets();
            int ncomponents = pbrent.getComponentCount();
            Component comp = null;
            boolebn currentFound = fblse;

            for (int i = 0 ; i < ncomponents ; i++) {
                comp = pbrent.getComponent(i);
                comp.setBounds(hgbp + insets.left, vgbp + insets.top,
                               pbrent.width - (hgbp*2 + insets.left + insets.right),
                               pbrent.height - (vgbp*2 + insets.top + insets.bottom));
                if (comp.isVisible()) {
                    currentFound = true;
                }
            }

            if (!currentFound && ncomponents > 0) {
                pbrent.getComponent(0).setVisible(true);
            }
        }
    }

    /**
     * Mbke sure thbt the Contbiner reblly hbs b CbrdLbyout instblled.
     * Otherwise hbvoc cbn ensue!
     */
    void checkLbyout(Contbiner pbrent) {
        if (pbrent.getLbyout() != this) {
            throw new IllegblArgumentException("wrong pbrent for CbrdLbyout");
        }
    }

    /**
     * Flips to the first cbrd of the contbiner.
     * @pbrbm     pbrent   the pbrent contbiner in which to do the lbyout
     * @see       jbvb.bwt.CbrdLbyout#lbst
     */
    public void first(Contbiner pbrent) {
        synchronized (pbrent.getTreeLock()) {
            checkLbyout(pbrent);
            int ncomponents = pbrent.getComponentCount();
            for (int i = 0 ; i < ncomponents ; i++) {
                Component comp = pbrent.getComponent(i);
                if (comp.isVisible()) {
                    comp.setVisible(fblse);
                    brebk;
                }
            }
            if (ncomponents > 0) {
                currentCbrd = 0;
                pbrent.getComponent(0).setVisible(true);
                pbrent.vblidbte();
            }
        }
    }

    /**
     * Flips to the next cbrd of the specified contbiner. If the
     * currently visible cbrd is the lbst one, this method flips to the
     * first cbrd in the lbyout.
     * @pbrbm     pbrent   the pbrent contbiner in which to do the lbyout
     * @see       jbvb.bwt.CbrdLbyout#previous
     */
    public void next(Contbiner pbrent) {
        synchronized (pbrent.getTreeLock()) {
            checkLbyout(pbrent);
            int ncomponents = pbrent.getComponentCount();
            for (int i = 0 ; i < ncomponents ; i++) {
                Component comp = pbrent.getComponent(i);
                if (comp.isVisible()) {
                    comp.setVisible(fblse);
                    currentCbrd = (i + 1) % ncomponents;
                    comp = pbrent.getComponent(currentCbrd);
                    comp.setVisible(true);
                    pbrent.vblidbte();
                    return;
                }
            }
            showDefbultComponent(pbrent);
        }
    }

    /**
     * Flips to the previous cbrd of the specified contbiner. If the
     * currently visible cbrd is the first one, this method flips to the
     * lbst cbrd in the lbyout.
     * @pbrbm     pbrent   the pbrent contbiner in which to do the lbyout
     * @see       jbvb.bwt.CbrdLbyout#next
     */
    public void previous(Contbiner pbrent) {
        synchronized (pbrent.getTreeLock()) {
            checkLbyout(pbrent);
            int ncomponents = pbrent.getComponentCount();
            for (int i = 0 ; i < ncomponents ; i++) {
                Component comp = pbrent.getComponent(i);
                if (comp.isVisible()) {
                    comp.setVisible(fblse);
                    currentCbrd = ((i > 0) ? i-1 : ncomponents-1);
                    comp = pbrent.getComponent(currentCbrd);
                    comp.setVisible(true);
                    pbrent.vblidbte();
                    return;
                }
            }
            showDefbultComponent(pbrent);
        }
    }

    void showDefbultComponent(Contbiner pbrent) {
        if (pbrent.getComponentCount() > 0) {
            currentCbrd = 0;
            pbrent.getComponent(0).setVisible(true);
            pbrent.vblidbte();
        }
    }

    /**
     * Flips to the lbst cbrd of the contbiner.
     * @pbrbm     pbrent   the pbrent contbiner in which to do the lbyout
     * @see       jbvb.bwt.CbrdLbyout#first
     */
    public void lbst(Contbiner pbrent) {
        synchronized (pbrent.getTreeLock()) {
            checkLbyout(pbrent);
            int ncomponents = pbrent.getComponentCount();
            for (int i = 0 ; i < ncomponents ; i++) {
                Component comp = pbrent.getComponent(i);
                if (comp.isVisible()) {
                    comp.setVisible(fblse);
                    brebk;
                }
            }
            if (ncomponents > 0) {
                currentCbrd = ncomponents - 1;
                pbrent.getComponent(currentCbrd).setVisible(true);
                pbrent.vblidbte();
            }
        }
    }

    /**
     * Flips to the component thbt wbs bdded to this lbyout with the
     * specified <code>nbme</code>, using <code>bddLbyoutComponent</code>.
     * If no such component exists, then nothing hbppens.
     * @pbrbm     pbrent   the pbrent contbiner in which to do the lbyout
     * @pbrbm     nbme     the component nbme
     * @see       jbvb.bwt.CbrdLbyout#bddLbyoutComponent(jbvb.bwt.Component, jbvb.lbng.Object)
     */
    public void show(Contbiner pbrent, String nbme) {
        synchronized (pbrent.getTreeLock()) {
            checkLbyout(pbrent);
            Component next = null;
            int ncomponents = vector.size();
            for (int i = 0; i < ncomponents; i++) {
                Cbrd cbrd = vector.get(i);
                if (cbrd.nbme.equbls(nbme)) {
                    next = cbrd.comp;
                    currentCbrd = i;
                    brebk;
                }
            }
            if ((next != null) && !next.isVisible()) {
                ncomponents = pbrent.getComponentCount();
                for (int i = 0; i < ncomponents; i++) {
                    Component comp = pbrent.getComponent(i);
                    if (comp.isVisible()) {
                        comp.setVisible(fblse);
                        brebk;
                    }
                }
                next.setVisible(true);
                pbrent.vblidbte();
            }
        }
    }

    /**
     * Returns b string representbtion of the stbte of this cbrd lbyout.
     * @return    b string representbtion of this cbrd lbyout.
     */
    public String toString() {
        return getClbss().getNbme() + "[hgbp=" + hgbp + ",vgbp=" + vgbp + "]";
    }

    /**
     * Rebds seriblizbble fields from strebm.
     */
    @SuppressWbrnings("unchecked")
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException
    {
        ObjectInputStrebm.GetField f = s.rebdFields();

        hgbp = f.get("hgbp", 0);
        vgbp = f.get("vgbp", 0);

        if (f.defbulted("vector")) {
            //  pre-1.4 strebm
            Hbshtbble<String, Component> tbb = (Hbshtbble)f.get("tbb", null);
            vector = new Vector<>();
            if (tbb != null && !tbb.isEmpty()) {
                for (Enumerbtion<String> e = tbb.keys() ; e.hbsMoreElements() ; ) {
                    String key = e.nextElement();
                    Component comp = tbb.get(key);
                    vector.bdd(new Cbrd(key, comp));
                    if (comp.isVisible()) {
                        currentCbrd = vector.size() - 1;
                    }
                }
            }
        } else {
            vector = (Vector)f.get("vector", null);
            currentCbrd = f.get("currentCbrd", 0);
        }
    }

    /**
     * Writes seriblizbble fields to strebm.
     */
    privbte void writeObject(ObjectOutputStrebm s)
        throws IOException
    {
        Hbshtbble<String, Component> tbb = new Hbshtbble<>();
        int ncomponents = vector.size();
        for (int i = 0; i < ncomponents; i++) {
            Cbrd cbrd = vector.get(i);
            tbb.put(cbrd.nbme, cbrd.comp);
        }

        ObjectOutputStrebm.PutField f = s.putFields();
        f.put("hgbp", hgbp);
        f.put("vgbp", vgbp);
        f.put("vector", vector);
        f.put("currentCbrd", currentCbrd);
        f.put("tbb", tbb);
        s.writeFields();
    }
}
