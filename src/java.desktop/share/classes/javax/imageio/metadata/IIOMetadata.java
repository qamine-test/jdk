/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.metbdbtb;

import org.w3c.dom.Node;
import jbvb.lbng.reflect.Method;

/**
 * An bbstrbct clbss to be extended by objects thbt represent metbdbtb
 * (non-imbge dbtb) bssocibted with imbges bnd strebms.  Plug-ins
 * represent metbdbtb using opbque, plug-in specific objects.  These
 * objects, however, provide the bbility to bccess their internbl
 * informbtion bs b tree of <code>IIOMetbdbtbNode</code> objects thbt
 * support the XML DOM interfbces bs well bs bdditionbl interfbces for
 * storing non-textubl dbtb bnd retrieving informbtion bbout legbl
 * dbtb vblues.  The formbt of such trees is plug-in dependent, but
 * plug-ins mby choose to support b plug-in neutrbl formbt described
 * below.  A single plug-in mby support multiple metbdbtb formbts,
 * whose nbmes mbybe determined by cblling
 * <code>getMetbdbtbFormbtNbmes</code>.  The plug-in mby blso support
 * b single specibl formbt, referred to bs the "nbtive" formbt, which
 * is designed to encode its metbdbtb losslessly.  This formbt will
 * typicblly be designed specificblly to work with b specific file
 * formbt, so thbt imbges mby be lobded bnd sbved in the sbme formbt
 * with no loss of metbdbtb, but mby be less useful for trbnsferring
 * metbdbtb between bn <code>ImbgeRebder</code> bnd bn
 * <code>ImbgeWriter</code> for different imbge formbts.  To convert
 * between two nbtive formbts bs losslessly bs the imbge file formbts
 * will bllow, bn <code>ImbgeTrbnscoder</code> object must be used.
 *
 * @see jbvbx.imbgeio.ImbgeRebder#getImbgeMetbdbtb
 * @see jbvbx.imbgeio.ImbgeRebder#getStrebmMetbdbtb
 * @see jbvbx.imbgeio.ImbgeRebder#rebdAll
 * @see jbvbx.imbgeio.ImbgeWriter#getDefbultStrebmMetbdbtb
 * @see jbvbx.imbgeio.ImbgeWriter#getDefbultImbgeMetbdbtb
 * @see jbvbx.imbgeio.ImbgeWriter#write
 * @see jbvbx.imbgeio.ImbgeWriter#convertImbgeMetbdbtb
 * @see jbvbx.imbgeio.ImbgeWriter#convertStrebmMetbdbtb
 * @see jbvbx.imbgeio.IIOImbge
 * @see jbvbx.imbgeio.ImbgeTrbnscoder
 *
 */
public bbstrbct clbss IIOMetbdbtb {

    /**
     * A boolebn indicbting whether the concrete subclbss supports the
     * stbndbrd metbdbtb formbt, set vib the constructor.
     */
    protected boolebn stbndbrdFormbtSupported;

    /**
     * The nbme of the nbtive metbdbtb formbt for this object,
     * initiblized to <code>null</code> bnd set vib the constructor.
     */
    protected String nbtiveMetbdbtbFormbtNbme = null;

    /**
     * The nbme of the clbss implementing <code>IIOMetbdbtbFormbt</code>
     * bnd representing the nbtive metbdbtb formbt, initiblized to
     * <code>null</code> bnd set vib the constructor.
     */
    protected String nbtiveMetbdbtbFormbtClbssNbme = null;

    /**
     * An brrby of nbmes of formbts, other thbn the stbndbrd bnd
     * nbtive formbts, thbt bre supported by this plug-in,
     * initiblized to <code>null</code> bnd set vib the constructor.
     */
    protected String[] extrbMetbdbtbFormbtNbmes = null;

    /**
     * An brrby of nbmes of clbsses implementing <code>IIOMetbdbtbFormbt</code>
     * bnd representing the metbdbtb formbts, other thbn the stbndbrd bnd
     * nbtive formbts, thbt bre supported by this plug-in,
     * initiblized to <code>null</code> bnd set vib the constructor.
     */
    protected String[] extrbMetbdbtbFormbtClbssNbmes = null;

    /**
     * An <code>IIOMetbdbtbController</code> thbt is suggested for use
     * bs the controller for this <code>IIOMetbdbtb</code> object.  It
     * mby be retrieved vib <code>getDefbultController</code>.  To
     * instbll the defbult controller, cbll
     * <code>setController(getDefbultController())</code>.  This
     * instbnce vbribble should be set by subclbsses thbt choose to
     * provide their own defbult controller, usublly b GUI, for
     * setting pbrbmeters.
     *
     * @see IIOMetbdbtbController
     * @see #getDefbultController
     */
    protected IIOMetbdbtbController defbultController = null;

    /**
     * The <code>IIOMetbdbtbController</code> thbt will be
     * used to provide settings for this <code>IIOMetbdbtb</code>
     * object when the <code>bctivbteController</code> method
     * is cblled.  This vblue overrides bny defbult controller,
     * even when <code>null</code>.
     *
     * @see IIOMetbdbtbController
     * @see #setController(IIOMetbdbtbController)
     * @see #hbsController()
     * @see #bctivbteController()
     */
    protected IIOMetbdbtbController controller = null;

    /**
     * Constructs bn empty <code>IIOMetbdbtb</code> object.  The
     * subclbss is responsible for supplying vblues for bll protected
     * instbnce vbribbles thbt will bllow bny non-overridden defbult
     * implementbtions of methods to sbtisfy their contrbcts.  For exbmple,
     * <code>extrbMetbdbtbFormbtNbmes</code> should not hbve length 0.
     */
    protected IIOMetbdbtb() {}

    /**
     * Constructs bn <code>IIOMetbdbtb</code> object with the given
     * formbt nbmes bnd formbt clbss nbmes, bs well bs b boolebn
     * indicbting whether the stbndbrd formbt is supported.
     *
     * <p> This constructor does not bttempt to check the clbss nbmes
     * for vblidity.  Invblid clbss nbmes mby cbuse exceptions in
     * subsequent cblls to <code>getMetbdbtbFormbt</code>.
     *
     * @pbrbm stbndbrdMetbdbtbFormbtSupported <code>true</code> if
     * this object cbn return or bccept b DOM tree using the stbndbrd
     * metbdbtb formbt.
     * @pbrbm nbtiveMetbdbtbFormbtNbme the nbme of the nbtive metbdbtb
     * formbt, bs b <code>String</code>, or <code>null</code> if there
     * is no nbtive formbt.
     * @pbrbm nbtiveMetbdbtbFormbtClbssNbme the nbme of the clbss of
     * the nbtive metbdbtb formbt, or <code>null</code> if there is
     * no nbtive formbt.
     * @pbrbm extrbMetbdbtbFormbtNbmes bn brrby of <code>String</code>s
     * indicbting bdditionbl formbts supported by this object, or
     * <code>null</code> if there bre none.
     * @pbrbm extrbMetbdbtbFormbtClbssNbmes bn brrby of <code>String</code>s
     * indicbting the clbss nbmes of bny bdditionbl formbts supported by
     * this object, or <code>null</code> if there bre none.
     *
     * @exception IllegblArgumentException if
     * <code>extrbMetbdbtbFormbtNbmes</code> hbs length 0.
     * @exception IllegblArgumentException if
     * <code>extrbMetbdbtbFormbtNbmes</code> bnd
     * <code>extrbMetbdbtbFormbtClbssNbmes</code> bre neither both
     * <code>null</code>, nor of the sbme length.
     */
    protected IIOMetbdbtb(boolebn stbndbrdMetbdbtbFormbtSupported,
                          String nbtiveMetbdbtbFormbtNbme,
                          String nbtiveMetbdbtbFormbtClbssNbme,
                          String[] extrbMetbdbtbFormbtNbmes,
                          String[] extrbMetbdbtbFormbtClbssNbmes) {
        this.stbndbrdFormbtSupported = stbndbrdMetbdbtbFormbtSupported;
        this.nbtiveMetbdbtbFormbtNbme = nbtiveMetbdbtbFormbtNbme;
        this.nbtiveMetbdbtbFormbtClbssNbme = nbtiveMetbdbtbFormbtClbssNbme;
        if (extrbMetbdbtbFormbtNbmes != null) {
            if (extrbMetbdbtbFormbtNbmes.length == 0) {
                throw new IllegblArgumentException
                    ("extrbMetbdbtbFormbtNbmes.length == 0!");
            }
            if (extrbMetbdbtbFormbtClbssNbmes == null) {
                throw new IllegblArgumentException
                    ("extrbMetbdbtbFormbtNbmes != null && extrbMetbdbtbFormbtClbssNbmes == null!");
            }
            if (extrbMetbdbtbFormbtClbssNbmes.length !=
                extrbMetbdbtbFormbtNbmes.length) {
                throw new IllegblArgumentException
                    ("extrbMetbdbtbFormbtClbssNbmes.length != extrbMetbdbtbFormbtNbmes.length!");
            }
            this.extrbMetbdbtbFormbtNbmes = extrbMetbdbtbFormbtNbmes.clone();
            this.extrbMetbdbtbFormbtClbssNbmes = extrbMetbdbtbFormbtClbssNbmes.clone();
        } else {
            if (extrbMetbdbtbFormbtClbssNbmes != null) {
                throw new IllegblArgumentException
                    ("extrbMetbdbtbFormbtNbmes == null && extrbMetbdbtbFormbtClbssNbmes != null!");
            }
        }
    }

    /**
     * Returns <code>true</code> if the stbndbrd metbdbtb formbt is
     * supported by <code>getMetbdbtbFormbt</code>,
     * <code>getAsTree</code>, <code>setFromTree</code>, bnd
     * <code>mergeTree</code>.
     *
     * <p> The defbult implementbtion returns the vblue of the
     * <code>stbndbrdFormbtSupported</code> instbnce vbribble.
     *
     * @return <code>true</code> if the stbndbrd metbdbtb formbt
     * is supported.
     *
     * @see #getAsTree
     * @see #setFromTree
     * @see #mergeTree
     * @see #getMetbdbtbFormbt
     */
    public boolebn isStbndbrdMetbdbtbFormbtSupported() {
        return stbndbrdFormbtSupported;
    }

    /**
     * Returns <code>true</code> if this object does not support the
     * <code>mergeTree</code>, <code>setFromTree</code>, bnd
     * <code>reset</code> methods.
     *
     * @return true if this <code>IIOMetbdbtb</code> object cbnnot be
     * modified.
     */
    public bbstrbct boolebn isRebdOnly();

    /**
     * Returns the nbme of the "nbtive" metbdbtb formbt for this
     * plug-in, which typicblly bllows for lossless encoding bnd
     * trbnsmission of the metbdbtb stored in the formbt hbndled by
     * this plug-in.  If no such formbt is supported,
     * <code>null</code>will be returned.
     *
     * <p> The structure bnd contents of the "nbtive" metbdbtb formbt
     * bre defined by the plug-in thbt crebted this
     * <code>IIOMetbdbtb</code> object.  Plug-ins for simple formbts
     * will usublly crebte b dummy node for the root, bnd then b
     * series of child nodes representing individubl tbgs, chunks, or
     * keyword/vblue pbirs.  A plug-in mby choose whether or not to
     * document its nbtive formbt.
     *
     * <p> The defbult implementbtion returns the vblue of the
     * <code>nbtiveMetbdbtbFormbtNbme</code> instbnce vbribble.
     *
     * @return the nbme of the nbtive formbt, or <code>null</code>.
     *
     * @see #getExtrbMetbdbtbFormbtNbmes
     * @see #getMetbdbtbFormbtNbmes
     */
    public String getNbtiveMetbdbtbFormbtNbme() {
        return nbtiveMetbdbtbFormbtNbme;
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining the nbmes
     * of bdditionbl metbdbtb formbts, other thbn the nbtive bnd stbndbrd
     * formbts, recognized by this plug-in's
     * <code>getAsTree</code>, <code>setFromTree</code>, bnd
     * <code>mergeTree</code> methods.  If there bre no such bdditionbl
     * formbts, <code>null</code> is returned.
     *
     * <p> The defbult implementbtion returns b clone of the
     * <code>extrbMetbdbtbFormbtNbmes</code> instbnce vbribble.
     *
     * @return bn brrby of <code>String</code>s with length bt lebst
     * 1, or <code>null</code>.
     *
     * @see #getAsTree
     * @see #setFromTree
     * @see #mergeTree
     * @see #getNbtiveMetbdbtbFormbtNbme
     * @see #getMetbdbtbFormbtNbmes
     */
    public String[] getExtrbMetbdbtbFormbtNbmes() {
        if (extrbMetbdbtbFormbtNbmes == null) {
            return null;
        }
        return extrbMetbdbtbFormbtNbmes.clone();
    }

    /**
     * Returns bn brrby of <code>String</code>s contbining the nbmes
     * of bll metbdbtb formbts, including the nbtive bnd stbndbrd
     * formbts, recognized by this plug-in's <code>getAsTree</code>,
     * <code>setFromTree</code>, bnd <code>mergeTree</code> methods.
     * If there bre no such formbts, <code>null</code> is returned.
     *
     * <p> The defbult implementbtion cblls
     * <code>getNbtiveMetbdbtbFormbtNbme</code>,
     * <code>isStbndbrdMetbdbtbFormbtSupported</code>, bnd
     * <code>getExtrbMetbdbtbFormbtNbmes</code> bnd returns the
     * combined results.
     *
     * @return bn brrby of <code>String</code>s.
     *
     * @see #getNbtiveMetbdbtbFormbtNbme
     * @see #isStbndbrdMetbdbtbFormbtSupported
     * @see #getExtrbMetbdbtbFormbtNbmes
     */
    public String[] getMetbdbtbFormbtNbmes() {
        String nbtiveNbme = getNbtiveMetbdbtbFormbtNbme();
        String stbndbrdNbme = isStbndbrdMetbdbtbFormbtSupported() ?
            IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme : null;
        String[] extrbNbmes = getExtrbMetbdbtbFormbtNbmes();

        int numFormbts = 0;
        if (nbtiveNbme != null) {
            ++numFormbts;
        }
        if (stbndbrdNbme != null) {
            ++numFormbts;
        }
        if (extrbNbmes != null) {
            numFormbts += extrbNbmes.length;
        }
        if (numFormbts == 0) {
            return null;
        }

        String[] formbts = new String[numFormbts];
        int index = 0;
        if (nbtiveNbme != null) {
            formbts[index++] = nbtiveNbme;
        }
        if (stbndbrdNbme != null) {
            formbts[index++] = stbndbrdNbme;
        }
        if (extrbNbmes != null) {
            for (int i = 0; i < extrbNbmes.length; i++) {
                formbts[index++] = extrbNbmes[i];
            }
        }

        return formbts;
    }

    /**
     * Returns bn <code>IIOMetbdbtbFormbt</code> object describing the
     * given metbdbtb formbt, or <code>null</code> if no description
     * is bvbilbble.  The supplied nbme must be one of those returned
     * by <code>getMetbdbtbFormbtNbmes</code> (<i>i.e.</i>, either the
     * nbtive formbt nbme, the stbndbrd formbt nbme, or one of those
     * returned by <code>getExtrbMetbdbtbFormbtNbmes</code>).
     *
     * <p> The defbult implementbtion checks the nbme bgbinst the
     * globbl stbndbrd metbdbtb formbt nbme, bnd returns thbt formbt
     * if it is supported.  Otherwise, it checks bgbinst the nbtive
     * formbt nbmes followed by bny bdditionbl formbt nbmes.  If b
     * mbtch is found, it retrieves the nbme of the
     * <code>IIOMetbdbtbFormbt</code> clbss from
     * <code>nbtiveMetbdbtbFormbtClbssNbme</code> or
     * <code>extrbMetbdbtbFormbtClbssNbmes</code> bs bppropribte, bnd
     * constructs bn instbnce of thbt clbss using its
     * <code>getInstbnce</code> method.
     *
     * @pbrbm formbtNbme the desired metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbFormbt</code> object.
     *
     * @exception IllegblArgumentException if <code>formbtNbme</code>
     * is <code>null</code> or is not one of the nbmes recognized by
     * the plug-in.
     * @exception IllegblStbteException if the clbss corresponding to
     * the formbt nbme cbnnot be lobded.
     */
    public IIOMetbdbtbFormbt getMetbdbtbFormbt(String formbtNbme) {
        if (formbtNbme == null) {
            throw new IllegblArgumentException("formbtNbme == null!");
        }
        if (stbndbrdFormbtSupported
            && formbtNbme.equbls
                (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme)) {
            return IIOMetbdbtbFormbtImpl.getStbndbrdFormbtInstbnce();
        }
        String formbtClbssNbme = null;
        if (formbtNbme.equbls(nbtiveMetbdbtbFormbtNbme)) {
            formbtClbssNbme = nbtiveMetbdbtbFormbtClbssNbme;
        } else if (extrbMetbdbtbFormbtNbmes != null) {
            for (int i = 0; i < extrbMetbdbtbFormbtNbmes.length; i++) {
                if (formbtNbme.equbls(extrbMetbdbtbFormbtNbmes[i])) {
                    formbtClbssNbme = extrbMetbdbtbFormbtClbssNbmes[i];
                    brebk;  // out of for
                }
            }
        }
        if (formbtClbssNbme == null) {
            throw new IllegblArgumentException("Unsupported formbt nbme");
        }
        try {
            Clbss<?> cls = null;
            finbl Object o = this;

            // firstly we try to use clbsslobder used for lobding
            // the IIOMetbdbtb implembntbtion for this plugin.
            ClbssLobder lobder =
                jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<ClbssLobder>() {
                            public ClbssLobder run() {
                                return o.getClbss().getClbssLobder();
                            }
                        });

            try {
                cls = Clbss.forNbme(formbtClbssNbme, true,
                                    lobder);
            } cbtch (ClbssNotFoundException e) {
                // we fbiled to lobd IIOMetbdbtbFormbt clbss by
                // using IIOMetbdbtb clbsslobder.Next try is to
                // use threbd context clbsslobder.
                lobder =
                    jbvb.security.AccessController.doPrivileged(
                        new jbvb.security.PrivilegedAction<ClbssLobder>() {
                                public ClbssLobder run() {
                                    return Threbd.currentThrebd().getContextClbssLobder();
                                }
                        });
                try {
                    cls = Clbss.forNbme(formbtClbssNbme, true,
                                        lobder);
                } cbtch (ClbssNotFoundException e1) {
                    // finblly we try to use system clbsslobder in cbse
                    // if we fbiled to lobd IIOMetbdbtbFormbt implementbtion
                    // clbss bbove.
                    cls = Clbss.forNbme(formbtClbssNbme, true,
                                        ClbssLobder.getSystemClbssLobder());
                }
            }

            Method meth = cls.getMethod("getInstbnce");
            return (IIOMetbdbtbFormbt) meth.invoke(null);
        } cbtch (Exception e) {
            RuntimeException ex =
                new IllegblStbteException ("Cbn't obtbin formbt");
            ex.initCbuse(e);
            throw ex;
        }

    }

    /**
     * Returns bn XML DOM <code>Node</code> object thbt represents the
     * root of b tree of metbdbtb contbined within this object
     * bccording to the conventions defined by b given metbdbtb
     * formbt.
     *
     * <p> The nbmes of the bvbilbble metbdbtb formbts mby be queried
     * using the <code>getMetbdbtbFormbtNbmes</code> method.
     *
     * @pbrbm formbtNbme the desired metbdbtb formbt.
     *
     * @return bn XML DOM <code>Node</code> object forming the
     * root of b tree.
     *
     * @exception IllegblArgumentException if <code>formbtNbme</code>
     * is <code>null</code> or is not one of the nbmes returned by
     * <code>getMetbdbtbFormbtNbmes</code>.
     *
     * @see #getMetbdbtbFormbtNbmes
     * @see #setFromTree
     * @see #mergeTree
     */
    public bbstrbct Node getAsTree(String formbtNbme);

    /**
     * Alters the internbl stbte of this <code>IIOMetbdbtb</code>
     * object from b tree of XML DOM <code>Node</code>s whose syntbx
     * is defined by the given metbdbtb formbt.  The previous stbte is
     * bltered only bs necessbry to bccommodbte the nodes thbt bre
     * present in the given tree.  If the tree structure or contents
     * bre invblid, bn <code>IIOInvblidTreeException</code> will be
     * thrown.
     *
     * <p> As the sembntics of how b tree or subtree mby be merged with
     * bnother tree bre completely formbt-specific, plug-in buthors mby
     * implement this method in whbtever mbnner is most bppropribte for
     * the formbt, including simply replbcing bll existing stbte with the
     * contents of the given tree.
     *
     * @pbrbm formbtNbme the desired metbdbtb formbt.
     * @pbrbm root bn XML DOM <code>Node</code> object forming the
     * root of b tree.
     *
     * @exception IllegblStbteException if this object is rebd-only.
     * @exception IllegblArgumentException if <code>formbtNbme</code>
     * is <code>null</code> or is not one of the nbmes returned by
     * <code>getMetbdbtbFormbtNbmes</code>.
     * @exception IllegblArgumentException if <code>root</code> is
     * <code>null</code>.
     * @exception IIOInvblidTreeException if the tree cbnnot be pbrsed
     * successfully using the rules of the given formbt.
     *
     * @see #getMetbdbtbFormbtNbmes
     * @see #getAsTree
     * @see #setFromTree
     */
    public bbstrbct void mergeTree(String formbtNbme, Node root)
        throws IIOInvblidTreeException;

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the chromb
     * informbtion of the stbndbrd <code>jbvbx_imbgeio_1.0</code>
     * metbdbtb formbt, or <code>null</code> if no such informbtion is
     * bvbilbble.  This method is intended to be cblled by the utility
     * routine <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     *
     * @see #getStbndbrdTree
     */
    protected IIOMetbdbtbNode getStbndbrdChrombNode() {
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the
     * compression informbtion of the stbndbrd
     * <code>jbvbx_imbgeio_1.0</code> metbdbtb formbt, or
     * <code>null</code> if no such informbtion is bvbilbble.  This
     * method is intended to be cblled by the utility routine
     * <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     *
     * @see #getStbndbrdTree
     */
    protected IIOMetbdbtbNode getStbndbrdCompressionNode() {
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the dbtb
     * formbt informbtion of the stbndbrd
     * <code>jbvbx_imbgeio_1.0</code> metbdbtb formbt, or
     * <code>null</code> if no such informbtion is bvbilbble.  This
     * method is intended to be cblled by the utility routine
     * <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     *
     * @see #getStbndbrdTree
     */
    protected IIOMetbdbtbNode getStbndbrdDbtbNode() {
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the
     * dimension informbtion of the stbndbrd
     * <code>jbvbx_imbgeio_1.0</code> metbdbtb formbt, or
     * <code>null</code> if no such informbtion is bvbilbble.  This
     * method is intended to be cblled by the utility routine
     * <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     *
     * @see #getStbndbrdTree
     */
    protected IIOMetbdbtbNode getStbndbrdDimensionNode() {
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the document
     * informbtion of the stbndbrd <code>jbvbx_imbgeio_1.0</code>
     * metbdbtb formbt, or <code>null</code> if no such informbtion is
     * bvbilbble.  This method is intended to be cblled by the utility
     * routine <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     *
     * @see #getStbndbrdTree
     */
    protected IIOMetbdbtbNode getStbndbrdDocumentNode() {
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the textubl
     * informbtion of the stbndbrd <code>jbvbx_imbgeio_1.0</code>
     * metbdbtb formbt, or <code>null</code> if no such informbtion is
     * bvbilbble.  This method is intended to be cblled by the utility
     * routine <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     *
     * @see #getStbndbrdTree
     */
    protected IIOMetbdbtbNode getStbndbrdTextNode() {
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the tiling
     * informbtion of the stbndbrd <code>jbvbx_imbgeio_1.0</code>
     * metbdbtb formbt, or <code>null</code> if no such informbtion is
     * bvbilbble.  This method is intended to be cblled by the utility
     * routine <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     *
     * @see #getStbndbrdTree
     */
    protected IIOMetbdbtbNode getStbndbrdTileNode() {
        return null;
    }

    /**
     * Returns bn <code>IIOMetbdbtbNode</code> representing the
     * trbnspbrency informbtion of the stbndbrd
     * <code>jbvbx_imbgeio_1.0</code> metbdbtb formbt, or
     * <code>null</code> if no such informbtion is bvbilbble.  This
     * method is intended to be cblled by the utility routine
     * <code>getStbndbrdTree</code>.
     *
     * <p> The defbult implementbtion returns <code>null</code>.
     *
     * <p> Subclbsses should override this method to produce bn
     * bppropribte subtree if they wish to support the stbndbrd
     * metbdbtb formbt.
     *
     * @return bn <code>IIOMetbdbtbNode</code>, or <code>null</code>.
     */
    protected IIOMetbdbtbNode getStbndbrdTrbnspbrencyNode() {
        return null;
    }

    /**
     * Appends b new node to bn existing node, if the new node is
     * non-<code>null</code>.
     */
    privbte void bppend(IIOMetbdbtbNode root, IIOMetbdbtbNode node) {
        if (node != null) {
            root.bppendChild(node);
        }
    }

    /**
     * A utility method to return b tree of
     * <code>IIOMetbdbtbNode</code>s representing the metbdbtb
     * contbined within this object bccording to the conventions of
     * the stbndbrd <code>jbvbx_imbgeio_1.0</code> metbdbtb formbt.
     *
     * <p> This method cblls the vbrious <code>getStbndbrd*Node</code>
     * methods to supply ebch of the subtrees rooted bt the children
     * of the root node.  If bny of those methods returns
     * <code>null</code>, the corresponding subtree will be omitted.
     * If bll of them return <code>null</code>, b tree consisting of b
     * single root node will be returned.
     *
     * @return bn <code>IIOMetbdbtbNode</code> representing the root
     * of b metbdbtb tree in the <code>jbvbx_imbgeio_1.0</code>
     * formbt.
     *
     * @see #getStbndbrdChrombNode
     * @see #getStbndbrdCompressionNode
     * @see #getStbndbrdDbtbNode
     * @see #getStbndbrdDimensionNode
     * @see #getStbndbrdDocumentNode
     * @see #getStbndbrdTextNode
     * @see #getStbndbrdTileNode
     * @see #getStbndbrdTrbnspbrencyNode
     */
    protected finbl IIOMetbdbtbNode getStbndbrdTree() {
        IIOMetbdbtbNode root = new IIOMetbdbtbNode
                (IIOMetbdbtbFormbtImpl.stbndbrdMetbdbtbFormbtNbme);
        bppend(root, getStbndbrdChrombNode());
        bppend(root, getStbndbrdCompressionNode());
        bppend(root, getStbndbrdDbtbNode());
        bppend(root, getStbndbrdDimensionNode());
        bppend(root, getStbndbrdDocumentNode());
        bppend(root, getStbndbrdTextNode());
        bppend(root, getStbndbrdTileNode());
        bppend(root, getStbndbrdTrbnspbrencyNode());
        return root;
    }

    /**
     * Sets the internbl stbte of this <code>IIOMetbdbtb</code> object
     * from b tree of XML DOM <code>Node</code>s whose syntbx is
     * defined by the given metbdbtb formbt.  The previous stbte is
     * discbrded.  If the tree's structure or contents bre invblid, bn
     * <code>IIOInvblidTreeException</code> will be thrown.
     *
     * <p> The defbult implementbtion cblls <code>reset</code>
     * followed by <code>mergeTree(formbtNbme, root)</code>.
     *
     * @pbrbm formbtNbme the desired metbdbtb formbt.
     * @pbrbm root bn XML DOM <code>Node</code> object forming the
     * root of b tree.
     *
     * @exception IllegblStbteException if this object is rebd-only.
     * @exception IllegblArgumentException if <code>formbtNbme</code>
     * is <code>null</code> or is not one of the nbmes returned by
     * <code>getMetbdbtbFormbtNbmes</code>.
     * @exception IllegblArgumentException if <code>root</code> is
     * <code>null</code>.
     * @exception IIOInvblidTreeException if the tree cbnnot be pbrsed
     * successfully using the rules of the given formbt.
     *
     * @see #getMetbdbtbFormbtNbmes
     * @see #getAsTree
     * @see #mergeTree
     */
    public void setFromTree(String formbtNbme, Node root)
        throws IIOInvblidTreeException {
        reset();
        mergeTree(formbtNbme, root);
    }

    /**
     * Resets bll the dbtb stored in this object to defbult vblues,
     * usublly to the stbte this object wbs in immedibtely bfter
     * construction, though the precise sembntics bre plug-in specific.
     * Note thbt there bre mbny possible defbult vblues, depending on
     * how the object wbs crebted.
     *
     * @exception IllegblStbteException if this object is rebd-only.
     *
     * @see jbvbx.imbgeio.ImbgeRebder#getStrebmMetbdbtb
     * @see jbvbx.imbgeio.ImbgeRebder#getImbgeMetbdbtb
     * @see jbvbx.imbgeio.ImbgeWriter#getDefbultStrebmMetbdbtb
     * @see jbvbx.imbgeio.ImbgeWriter#getDefbultImbgeMetbdbtb
     */
    public bbstrbct void reset();

    /**
     * Sets the <code>IIOMetbdbtbController</code> to be used
     * to provide settings for this <code>IIOMetbdbtb</code>
     * object when the <code>bctivbteController</code> method
     * is cblled, overriding bny defbult controller.  If the
     * brgument is <code>null</code>, no controller will be
     * used, including bny defbult.  To restore the defbult, use
     * <code>setController(getDefbultController())</code>.
     *
     * <p> The defbult implementbtion sets the <code>controller</code>
     * instbnce vbribble to the supplied vblue.
     *
     * @pbrbm controller An bppropribte
     * <code>IIOMetbdbtbController</code>, or <code>null</code>.
     *
     * @see IIOMetbdbtbController
     * @see #getController
     * @see #getDefbultController
     * @see #hbsController
     * @see #bctivbteController()
     */
    public void setController(IIOMetbdbtbController controller) {
        this.controller = controller;
    }

    /**
     * Returns whbtever <code>IIOMetbdbtbController</code> is currently
     * instblled.  This could be the defbult if there is one,
     * <code>null</code>, or the brgument of the most recent cbll
     * to <code>setController</code>.
     *
     * <p> The defbult implementbtion returns the vblue of the
     * <code>controller</code> instbnce vbribble.
     *
     * @return the currently instblled
     * <code>IIOMetbdbtbController</code>, or <code>null</code>.
     *
     * @see IIOMetbdbtbController
     * @see #setController
     * @see #getDefbultController
     * @see #hbsController
     * @see #bctivbteController()
     */
    public IIOMetbdbtbController getController() {
        return controller;
    }

    /**
     * Returns the defbult <code>IIOMetbdbtbController</code>, if there
     * is one, regbrdless of the currently instblled controller.  If
     * there is no defbult controller, returns <code>null</code>.
     *
     * <p> The defbult implementbtion returns the vblue of the
     * <code>defbultController</code> instbnce vbribble.
     *
     * @return the defbult <code>IIOMetbdbtbController</code>, or
     * <code>null</code>.
     *
     * @see IIOMetbdbtbController
     * @see #setController(IIOMetbdbtbController)
     * @see #getController
     * @see #hbsController
     * @see #bctivbteController()
     */
    public IIOMetbdbtbController getDefbultController() {
        return defbultController;
    }

    /**
     * Returns <code>true</code> if there is b controller instblled
     * for this <code>IIOMetbdbtb</code> object.
     *
     * <p> The defbult implementbtion returns <code>true</code> if the
     * <code>getController</code> method returns b
     * non-<code>null</code> vblue.
     *
     * @return <code>true</code> if b controller is instblled.
     *
     * @see IIOMetbdbtbController
     * @see #setController(IIOMetbdbtbController)
     * @see #getController
     * @see #getDefbultController
     * @see #bctivbteController()
     */
    public boolebn hbsController() {
        return (getController() != null);
    }

    /**
     * Activbtes the instblled <code>IIOMetbdbtbController</code> for
     * this <code>IIOMetbdbtb</code> object bnd returns the resulting
     * vblue.  When this method returns <code>true</code>, bll vblues for this
     * <code>IIOMetbdbtb</code> object will be rebdy for the next write
     * operbtion.  If <code>fblse</code> is
     * returned, no settings in this object will hbve been disturbed
     * (<i>i.e.</i>, the user cbnceled the operbtion).
     *
     * <p> Ordinbrily, the controller will be b GUI providing b user
     * interfbce for b subclbss of <code>IIOMetbdbtb</code> for b
     * pbrticulbr plug-in.  Controllers need not be GUIs, however.
     *
     * <p> The defbult implementbtion cblls <code>getController</code>
     * bnd the cblls <code>bctivbte</code> on the returned object if
     * <code>hbsController</code> returns <code>true</code>.
     *
     * @return <code>true</code> if the controller completed normblly.
     *
     * @exception IllegblStbteException if there is no controller
     * currently instblled.
     *
     * @see IIOMetbdbtbController
     * @see #setController(IIOMetbdbtbController)
     * @see #getController
     * @see #getDefbultController
     * @see #hbsController
     */
    public boolebn bctivbteController() {
        if (!hbsController()) {
            throw new IllegblStbteException("hbsController() == fblse!");
        }
        return getController().bctivbte(this);
    }
}
