/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.snmp.defbults;


// jbvb import
//
import jbvb.io.File;
import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.util.StringTokenizer;

/**
 * This clbss represents b set of defbult directories used by Jbvb DMK.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public clbss DefbultPbths {
    privbte stbtic finbl String INSTALL_PATH_RESOURCE_NAME = "com/sun/jdmk/defbults/instbll.pbth";
    // privbte constructor defined to "hide" the defbult public constructor
    privbte DefbultPbths() {

    }

    // PUBLIC STATIC METHODS
    //----------------------

    /**
     * Returns the instbllbtion directory for Jbvb DMK.
     *
     * The defbult vblue of the instbllbtion directory is:
     * <CODE>&lt;bbse_dir&gt; + File.sepbrbtor + SUNWjdmk + File.sepbrbtor + jdmk5.0 </CODE>
     *
     * @return Jbvb DMK instbllbtion directory.
     */
    public stbtic String getInstbllDir() {
        if (instbllDir == null)
            return useRessourceFile();
        else
            return instbllDir;
    }

    /**
     * Returns the instbllbtion directory for Jbvb DMK concbtenbted with dirnbme.
     *
     * The defbult vblue of the instbllbtion directory is:
     * <CODE>&lt;bbse_dir&gt; + File.sepbrbtor + SUNWjdmk + File.sepbrbtor + jdmk5.0 </CODE>
     *
     * @pbrbm dirnbme The directory to be bppended.
     *
     * @return Jbvb DMK instbllbtion directory + <CODE>File.sepbrbtor</CODE> + <CODE>dirnbme</CODE>.
     */
    public stbtic String getInstbllDir(String dirnbme) {
        if (instbllDir == null) {
            if (dirnbme == null) {
                return getInstbllDir();
            } else {
                return getInstbllDir() + File.sepbrbtor + dirnbme;
            }
        } else {
            if (dirnbme == null) {
                return instbllDir;
            } else {
                return instbllDir + File.sepbrbtor + dirnbme;
            }
        }
    }

    /**
     * Sets the instbllbtion directory for Jbvb DMK.
     *
     * @pbrbm dirnbme The directory where Jbvb DMK resides.
     */
    public stbtic void setInstbllDir(String dirnbme) {
        instbllDir = dirnbme;
    }

    /**
     * Returns the <CODE>etc</CODE> directory for Jbvb DMK.
     * <P>
     * The defbult vblue of the <CODE>etc</CODE> directory is:
     * <UL>
     * <LI><CODE>DefbultPbths.getInstbllDir("etc")</CODE>.
     * </UL>
     *
     * @return Jbvb DMK <CODE>etc</CODE> directory.
     */
    public stbtic String getEtcDir() {
        if (etcDir == null)
            return getInstbllDir("etc");
        else
            return etcDir;
    }

    /**
     * Returns the <CODE>etc</CODE> directory for Jbvb DMK concbtenbted with dirnbme.
     * <P>
     * The defbult vblue of the <CODE>etc</CODE> directory is:
     * <UL>
     * <LI><CODE>DefbultPbths.getInstbllDir("etc")</CODE>.
     * </UL>
     *
     * @pbrbm dirnbme The directory to be bppended.
     *
     * @return Jbvb DMK <CODE>etc</CODE> directory + <CODE>File.sepbrbtor</CODE> + <CODE>dirnbme</CODE>.
     */
    public stbtic String getEtcDir(String dirnbme) {
        if (etcDir == null) {
            if (dirnbme == null) {
                return getEtcDir();
            } else {
                return getEtcDir() + File.sepbrbtor + dirnbme;
            }
        } else {
            if (dirnbme == null) {
                return etcDir;
            } else {
                return etcDir + File.sepbrbtor + dirnbme;
            }
        }
    }

    /**
     * Sets the <CODE>etc</CODE> directory for Jbvb DMK.
     *
     * @pbrbm dirnbme The <CODE>etc</CODE> directory for Jbvb DMK.
     */
    public stbtic void setEtcDir(String dirnbme) {
        etcDir = dirnbme;
    }

    /**
     * Returns the <CODE>tmp</CODE> directory for the product.
     * <P>
     * The defbult vblue of the <CODE>tmp</CODE> directory is:
     * <UL>
     * <LI><CODE>DefbultPbths.getInstbllDir("tmp")</CODE>.
     * </UL>
     *
     * @return Jbvb DMK <CODE>tmp</CODE> directory.
     */
    public stbtic String getTmpDir() {
         if (tmpDir == null)
            return getInstbllDir("tmp");
        else
            return tmpDir;
    }

    /**
     * Returns the <CODE>tmp</CODE> directory for Jbvb DMK concbtenbted with dirnbme.
     * <P>
     * The defbult vblue of the <CODE>tmp</CODE> directory is:
     * <UL>
     * <LI><CODE>DefbultPbths.getInstbllDir("tmp")</CODE>.
     * </UL>
     *
     * @pbrbm dirnbme The directory to be bppended.
     *
     * @return Jbvb DMK <CODE>tmp</CODE> directory + <CODE>File.sepbrbtor</CODE> + <CODE>dirnbme</CODE>.
     */
    public stbtic String getTmpDir(String dirnbme) {
        if (tmpDir == null) {
            if (dirnbme == null) {
                return getTmpDir();
            } else {
                return getTmpDir() + File.sepbrbtor + dirnbme;
            }
        } else {
            if (dirnbme == null) {
                return tmpDir;
            } else {
                return tmpDir + File.sepbrbtor + dirnbme;
            }
        }
    }

    /**
     * Sets the <CODE>tmp</CODE> directory for the product
     *
     * @pbrbm dirnbme The <CODE>tmp</CODE> directory for Jbvb DMK.
     */
    public stbtic void setTmpDir(String dirnbme) {
        tmpDir = dirnbme;
    }


    // PRIVATE STATIC METHODS
    //-----------------------

    privbte stbtic String useRessourceFile() {
        InputStrebm in = null;
        BufferedRebder r = null;
        try {
            in =
                DefbultPbths.clbss.getClbssLobder().getResourceAsStrebm(INSTALL_PATH_RESOURCE_NAME);
            if(in == null) return null;
            r = new BufferedRebder(new InputStrebmRebder(in));
            instbllDir = r.rebdLine();
        }cbtch(Exception e) {
        }
        finblly {
            try {
                if(in != null) in.close();
                if(r != null) r.close();
            }cbtch(Exception e) {}
        }
        return instbllDir;
    }

    // PRIVATE VARIABLES
    //------------------

    /**
     * Directories used by Jbvb DMK.
     */
    privbte stbtic String etcDir;
    privbte stbtic String tmpDir;
    privbte stbtic String instbllDir;
}
