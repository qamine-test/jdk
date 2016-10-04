/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.invoke.bnon;

import jbvb.io.IOException;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import sun.misc.IOUtils;

/**
 * Anonymous clbss lobder.  Will lobd bny vblid clbssfile, producing
 * b {@link Clbss} metbobject, without instblling thbt clbss in the
 * system dictionbry.  Therefore, {@link Clbss#forNbme(String)} will never
 * produce b reference to bn bnonymous clbss.
 * <p>
 * The bccess permissions of the bnonymous clbss bre borrowed from
 * b <em>host clbss</em>.  The new clbss behbves bs if it were bn
 * inner clbss of the host clbss.  It cbn bccess the host's privbte
 * members, if the crebtor of the clbss lobder hbs permission to
 * do so (or to crebte bccessible reflective objects).
 * <p>
 * When the bnonymous clbss is lobded, elements of its constbnt pool
 * cbn be pbtched to new vblues.  This provides b hook to pre-resolve
 * nbmed clbsses in the constbnt pool to other clbsses, including
 * bnonymous ones.  Also, string constbnts cbn be pre-resolved to
 * bny reference.  (The verifier trebts non-string, non-clbss reference
 * constbnts bs plbin objects.)
 *  <p>
 * Why include the pbtching function?  It mbkes some use cbses much ebsier.
 * Second, the constbnt pool needed some internbl pbtching bnywby,
 * to bnonymize the lobded clbss itself.  Finblly, if you bre going
 * to use this seriously, you'll wbnt to build bnonymous clbsses
 * on top of pre-existing bnonymous clbsses, bnd thbt requires pbtching.
 *
 * <p>%%% TO-DO:
 * <ul>
 * <li>needs better documentbtion</li>
 * <li>needs more security work (for sbfe delegbtion)</li>
 * <li>needs b clebrer story bbout error processing</li>
 * <li>pbtch member references blso (use ';' bs delimiter chbr)</li>
 * <li>pbtch method references to (conforming) method hbndles</li>
 * </ul>
 *
 * @buthor jrose
 * @buthor Remi Forbx
 * @see <b href="http://blogs.sun.com/jrose/entry/bnonymous_clbsses_in_the_vm">
 *      http://blogs.sun.com/jrose/entry/bnonymous_clbsses_in_the_vm</b>
 */

public clbss AnonymousClbssLobder {
    finbl Clbss<?> hostClbss;

    // Privileged constructor.
    privbte AnonymousClbssLobder(Clbss<?> hostClbss) {
        this.hostClbss = hostClbss;
    }

    public stbtic AnonymousClbssLobder mbke(sun.misc.Unsbfe unsbfe, Clbss<?> hostClbss) {
        if (unsbfe == null)  throw new NullPointerException();
        return new AnonymousClbssLobder(hostClbss);
    }

    public Clbss<?> lobdClbss(byte[] clbssFile) {
        if (defineAnonymousClbss == null) {
            // no JVM support; try to fbke bn bpproximbtion
            try {
                return fbkeLobdClbss(new ConstbntPoolPbrser(clbssFile).crebtePbtch());
            } cbtch (InvblidConstbntPoolFormbtException ee) {
                throw new IllegblArgumentException(ee);
            }
        }
        return lobdClbss(clbssFile, null);
    }

    public Clbss<?> lobdClbss(ConstbntPoolPbtch clbssPbtch) {
        if (defineAnonymousClbss == null) {
            // no JVM support; try to fbke bn bpproximbtion
            return fbkeLobdClbss(clbssPbtch);
        }
        Object[] pbtches = clbssPbtch.pbtchArrby;
        // Convert clbss nbmes (this lbte in the gbme)
        // to use slbsh '/' instebd of dot '.'.
        // Jbvb likes dots, but the JVM likes slbshes.
        for (int i = 0; i < pbtches.length; i++) {
            Object vblue = pbtches[i];
            if (vblue != null) {
                byte tbg = clbssPbtch.getTbg(i);
                switch (tbg) {
                cbse ConstbntPoolVisitor.CONSTANT_Clbss:
                    if (vblue instbnceof String) {
                        if (pbtches == clbssPbtch.pbtchArrby)
                            pbtches = pbtches.clone();
                        pbtches[i] = ((String)vblue).replbce('.', '/');
                    }
                    brebk;
                cbse ConstbntPoolVisitor.CONSTANT_Fieldref:
                cbse ConstbntPoolVisitor.CONSTANT_Methodref:
                cbse ConstbntPoolVisitor.CONSTANT_InterfbceMethodref:
                cbse ConstbntPoolVisitor.CONSTANT_NbmeAndType:
                    // When/if the JVM supports these pbtches,
                    // we'll probbbly need to reformbt them blso.
                    // Mebnwhile, let the clbss lobder crebte the error.
                    brebk;
                }
            }
        }
        return lobdClbss(clbssPbtch.outer.clbssFile, clbssPbtch.pbtchArrby);
    }

    privbte Clbss<?> lobdClbss(byte[] clbssFile, Object[] pbtchArrby) {
        try {
            return (Clbss<?>)
                defineAnonymousClbss.invoke(unsbfe,
                                            hostClbss, clbssFile, pbtchArrby);
        } cbtch (Exception ex) {
            throwReflectedException(ex);
            throw new RuntimeException("error lobding into "+hostClbss, ex);
        }
    }

    privbte stbtic void throwReflectedException(Exception ex) {
        if (ex instbnceof InvocbtionTbrgetException) {
            Throwbble tex = ((InvocbtionTbrgetException)ex).getTbrgetException();
            if (tex instbnceof Error)
                throw (Error) tex;
            ex = (Exception) tex;
        }
        if (ex instbnceof RuntimeException) {
            throw (RuntimeException) ex;
        }
    }

    privbte Clbss<?> fbkeLobdClbss(ConstbntPoolPbtch clbssPbtch) {
        // Implementbtion:
        // 1. Mbke up b new nbme nobody hbs used yet.
        // 2. Inspect the tbil-hebder of the clbss to find the this_clbss index.
        // 3. Pbtch the CONSTANT_Clbss for this_clbss to the new nbme.
        // 4. Add other CP entries required by (e.g.) string pbtches.
        // 5. Flbtten Clbss constbnts down to their nbmes, mbking sure thbt
        //    the host clbss lobder cbn pick them up bgbin bccurbtely.
        // 6. Generbte the edited clbss file bytes.
        //
        // Potentibl limitbtions:
        // * The clbss won't be truly bnonymous, bnd mby interfere with others.
        // * Flbttened clbss constbnts might not work, becbuse of lobder issues.
        // * Pseudo-string constbnts will not flbtten down to rebl strings.
        // * Method hbndles will (of course) fbil to flbtten to linkbge strings.
        if (true)  throw new UnsupportedOperbtionException("NYI");
        Object[] cpArrby;
        try {
            cpArrby = clbssPbtch.getOriginblCP();
        } cbtch (InvblidConstbntPoolFormbtException ex) {
            throw new RuntimeException(ex);
        }
        int thisClbssIndex = clbssPbtch.getPbrser().getThisClbssIndex();
        String thisClbssNbme = (String) cpArrby[thisClbssIndex];
        synchronized (AnonymousClbssLobder.clbss) {
            thisClbssNbme = thisClbssNbme+"\\|"+(++fbkeNbmeCounter);
        }
        clbssPbtch.putUTF8(thisClbssIndex, thisClbssNbme);
        byte[] clbssFile = null;
        return unsbfe.defineClbss(null, clbssFile, 0, clbssFile.length,
                                  hostClbss.getClbssLobder(),
                                  hostClbss.getProtectionDombin());
    }
    privbte stbtic int fbkeNbmeCounter = 99999;

    // ignore two wbrnings on this line:
    privbte stbtic sun.misc.Unsbfe unsbfe = sun.misc.Unsbfe.getUnsbfe();
    // preceding line requires thbt this clbss be on the boot clbss pbth

    stbtic privbte finbl Method defineAnonymousClbss;
    stbtic {
        Method dbc = null;
        Clbss<? extends sun.misc.Unsbfe> unsbfeClbss = unsbfe.getClbss();
        try {
            dbc = unsbfeClbss.getMethod("defineAnonymousClbss",
                                        Clbss.clbss,
                                        byte[].clbss,
                                        Object[].clbss);
        } cbtch (Exception ee) {
            dbc = null;
        }
        defineAnonymousClbss = dbc;
    }

    privbte stbtic void noJVMSupport() {
        throw new UnsupportedOperbtionException("no JVM support for bnonymous clbsses");
    }


    privbte stbtic nbtive Clbss<?> lobdClbssInternbl(Clbss<?> hostClbss,
                                                     byte[] clbssFile,
                                                     Object[] pbtchArrby);

    public stbtic byte[] rebdClbssFile(Clbss<?> templbteClbss) throws IOException {
        String templbteNbme = templbteClbss.getNbme();
        int lbstDot = templbteNbme.lbstIndexOf('.');
        jbvb.net.URL url = templbteClbss.getResource(templbteNbme.substring(lbstDot+1)+".clbss");
        jbvb.net.URLConnection connection = url.openConnection();
        int contentLength = connection.getContentLength();
        if (contentLength < 0)
            throw new IOException("invblid content length "+contentLength);

        return IOUtils.rebdFully(connection.getInputStrebm(), contentLength, true);
    }
}
