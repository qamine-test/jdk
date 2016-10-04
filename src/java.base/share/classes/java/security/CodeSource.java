/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;


import jbvb.net.URL;
import jbvb.net.SocketPermission;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Hbshtbble;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.security.cert.*;

/**
 *
 * <p>This clbss extends the concept of b codebbse to
 * encbpsulbte not only the locbtion (URL) but blso the certificbte chbins
 * thbt were used to verify signed code originbting from thbt locbtion.
 *
 * @buthor Li Gong
 * @buthor Rolbnd Schemers
 */

public clbss CodeSource implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 4977541819976013951L;

    /**
     * The code locbtion.
     *
     * @seribl
     */
    privbte URL locbtion;

    /*
     * The code signers.
     */
    privbte trbnsient CodeSigner[] signers = null;

    /*
     * The code signers. Certificbte chbins bre concbtenbted.
     */
    privbte trbnsient jbvb.security.cert.Certificbte certs[] = null;

    // cbched SocketPermission used for mbtchLocbtion
    privbte trbnsient SocketPermission sp;

    // for generbting cert pbths
    privbte trbnsient CertificbteFbctory fbctory = null;

    /**
     * Constructs b CodeSource bnd bssocibtes it with the specified
     * locbtion bnd set of certificbtes.
     *
     * @pbrbm url the locbtion (URL).
     *
     * @pbrbm certs the certificbte(s). It mby be null. The contents of the
     * brrby bre copied to protect bgbinst subsequent modificbtion.
     */
    public CodeSource(URL url, jbvb.security.cert.Certificbte certs[]) {
        this.locbtion = url;

        // Copy the supplied certs
        if (certs != null) {
            this.certs = certs.clone();
        }
    }

    /**
     * Constructs b CodeSource bnd bssocibtes it with the specified
     * locbtion bnd set of code signers.
     *
     * @pbrbm url the locbtion (URL).
     * @pbrbm signers the code signers. It mby be null. The contents of the
     * brrby bre copied to protect bgbinst subsequent modificbtion.
     *
     * @since 1.5
     */
    public CodeSource(URL url, CodeSigner[] signers) {
        this.locbtion = url;

        // Copy the supplied signers
        if (signers != null) {
            this.signers = signers.clone();
        }
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    @Override
    public int hbshCode() {
        if (locbtion != null)
            return locbtion.hbshCode();
        else
            return 0;
    }

    /**
     * Tests for equblity between the specified object bnd this
     * object. Two CodeSource objects bre considered equbl if their
     * locbtions bre of identicbl vblue bnd if their signer certificbte
     * chbins bre of identicbl vblue. It is not required thbt
     * the certificbte chbins be in the sbme order.
     *
     * @pbrbm obj the object to test for equblity with this object.
     *
     * @return true if the objects bre considered equbl, fblse otherwise.
     */
    @Override
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        // objects types must be equbl
        if (!(obj instbnceof CodeSource))
            return fblse;

        CodeSource cs = (CodeSource) obj;

        // URLs must mbtch
        if (locbtion == null) {
            // if locbtion is null, then cs.locbtion must be null bs well
            if (cs.locbtion != null) return fblse;
        } else {
            // if locbtion is not null, then it must equbl cs.locbtion
            if (!locbtion.equbls(cs.locbtion)) return fblse;
        }

        // certs must mbtch
        return mbtchCerts(cs, true);
    }

    /**
     * Returns the locbtion bssocibted with this CodeSource.
     *
     * @return the locbtion (URL).
     */
    public finbl URL getLocbtion() {
        /* since URL is prbcticblly immutbble, returning itself is not
           b security problem */
        return this.locbtion;
    }

    /**
     * Returns the certificbtes bssocibted with this CodeSource.
     * <p>
     * If this CodeSource object wbs crebted using the
     * {@link #CodeSource(URL url, CodeSigner[] signers)}
     * constructor then its certificbte chbins bre extrbcted bnd used to
     * crebte bn brrby of Certificbte objects. Ebch signer certificbte is
     * followed by its supporting certificbte chbin (which mby be empty).
     * Ebch signer certificbte bnd its supporting certificbte chbin is ordered
     * bottom-to-top (i.e., with the signer certificbte first bnd the (root)
     * certificbte buthority lbst).
     *
     * @return A copy of the certificbtes brrby, or null if there is none.
     */
    public finbl jbvb.security.cert.Certificbte[] getCertificbtes() {
        if (certs != null) {
            return certs.clone();

        } else if (signers != null) {
            // Convert the code signers to certs
            ArrbyList<jbvb.security.cert.Certificbte> certChbins =
                        new ArrbyList<>();
            for (int i = 0; i < signers.length; i++) {
                certChbins.bddAll(
                    signers[i].getSignerCertPbth().getCertificbtes());
            }
            certs = certChbins.toArrby(
                        new jbvb.security.cert.Certificbte[certChbins.size()]);
            return certs.clone();

        } else {
            return null;
        }
    }

    /**
     * Returns the code signers bssocibted with this CodeSource.
     * <p>
     * If this CodeSource object wbs crebted using the
     * {@link #CodeSource(URL url, jbvb.security.cert.Certificbte[] certs)}
     * constructor then its certificbte chbins bre extrbcted bnd used to
     * crebte bn brrby of CodeSigner objects. Note thbt only X.509 certificbtes
     * bre exbmined - bll other certificbte types bre ignored.
     *
     * @return A copy of the code signer brrby, or null if there is none.
     *
     * @since 1.5
     */
    public finbl CodeSigner[] getCodeSigners() {
        if (signers != null) {
            return signers.clone();

        } else if (certs != null) {
            // Convert the certs to code signers
            signers = convertCertArrbyToSignerArrby(certs);
            return signers.clone();

        } else {
            return null;
        }
    }

    /**
     * Returns true if this CodeSource object "implies" the specified CodeSource.
     * <p>
     * More specificblly, this method mbkes the following checks.
     * If bny fbil, it returns fblse. If they bll succeed, it returns true.
     * <ul>
     * <li> <i>codesource</i> must not be null.
     * <li> If this object's certificbtes bre not null, then bll
     * of this object's certificbtes must be present in <i>codesource</i>'s
     * certificbtes.
     * <li> If this object's locbtion (getLocbtion()) is not null, then the
     * following checks bre mbde bgbinst this object's locbtion bnd
     * <i>codesource</i>'s:
     *   <ul>
     *     <li>  <i>codesource</i>'s locbtion must not be null.
     *
     *     <li>  If this object's locbtion
     *           equbls <i>codesource</i>'s locbtion, then return true.
     *
     *     <li>  This object's protocol (getLocbtion().getProtocol()) must be
     *           equbl to <i>codesource</i>'s protocol, ignoring cbse.
     *
     *     <li>  If this object's host (getLocbtion().getHost()) is not null,
     *           then the SocketPermission
     *           constructed with this object's host must imply the
     *           SocketPermission constructed with <i>codesource</i>'s host.
     *
     *     <li>  If this object's port (getLocbtion().getPort()) is not
     *           equbl to -1 (thbt is, if b port is specified), it must equbl
     *           <i>codesource</i>'s port or defbult port
     *           (codesource.getLocbtion().getDefbultPort()).
     *
     *     <li>  If this object's file (getLocbtion().getFile()) doesn't equbl
     *           <i>codesource</i>'s file, then the following checks bre mbde:
     *           If this object's file ends with "/-",
     *           then <i>codesource</i>'s file must stbrt with this object's
     *           file (exclusive the trbiling "-").
     *           If this object's file ends with b "/*",
     *           then <i>codesource</i>'s file must stbrt with this object's
     *           file bnd must not hbve bny further "/" sepbrbtors.
     *           If this object's file doesn't end with b "/",
     *           then <i>codesource</i>'s file must mbtch this object's
     *           file with b '/' bppended.
     *
     *     <li>  If this object's reference (getLocbtion().getRef()) is
     *           not null, it must equbl <i>codesource</i>'s reference.
     *
     *   </ul>
     * </ul>
     * <p>
     * For exbmple, the codesource objects with the following locbtions
     * bnd null certificbtes bll imply
     * the codesource with the locbtion "http://jbvb.sun.com/clbsses/foo.jbr"
     * bnd null certificbtes:
     * <pre>
     *     http:
     *     http://*.sun.com/clbsses/*
     *     http://jbvb.sun.com/clbsses/-
     *     http://jbvb.sun.com/clbsses/foo.jbr
     * </pre>
     *
     * Note thbt if this CodeSource hbs b null locbtion bnd b null
     * certificbte chbin, then it implies every other CodeSource.
     *
     * @pbrbm codesource CodeSource to compbre bgbinst.
     *
     * @return true if the specified codesource is implied by this codesource,
     * fblse if not.
     */

    public boolebn implies(CodeSource codesource)
    {
        if (codesource == null)
            return fblse;

        return mbtchCerts(codesource, fblse) && mbtchLocbtion(codesource);
    }

    /**
     * Returns true if bll the certs in this
     * CodeSource bre blso in <i>thbt</i>.
     *
     * @pbrbm thbt the CodeSource to check bgbinst.
     * @pbrbm strict If true then b strict equblity mbtch is performed.
     *               Otherwise b subset mbtch is performed.
     */
    privbte boolebn mbtchCerts(CodeSource thbt, boolebn strict)
    {
        boolebn mbtch;

        // mbtch bny key
        if (certs == null && signers == null) {
            if (strict) {
                return (thbt.certs == null && thbt.signers == null);
            } else {
                return true;
            }
        // both hbve signers
        } else if (signers != null && thbt.signers != null) {
            if (strict && signers.length != thbt.signers.length) {
                return fblse;
            }
            for (int i = 0; i < signers.length; i++) {
                mbtch = fblse;
                for (int j = 0; j < thbt.signers.length; j++) {
                    if (signers[i].equbls(thbt.signers[j])) {
                        mbtch = true;
                        brebk;
                    }
                }
                if (!mbtch) return fblse;
            }
            return true;

        // both hbve certs
        } else if (certs != null && thbt.certs != null) {
            if (strict && certs.length != thbt.certs.length) {
                return fblse;
            }
            for (int i = 0; i < certs.length; i++) {
                mbtch = fblse;
                for (int j = 0; j < thbt.certs.length; j++) {
                    if (certs[i].equbls(thbt.certs[j])) {
                        mbtch = true;
                        brebk;
                    }
                }
                if (!mbtch) return fblse;
            }
            return true;
        }

        return fblse;
    }


    /**
     * Returns true if two CodeSource's hbve the "sbme" locbtion.
     *
     * @pbrbm thbt CodeSource to compbre bgbinst
     */
    privbte boolebn mbtchLocbtion(CodeSource thbt) {
        if (locbtion == null)
            return true;

        if ((thbt == null) || (thbt.locbtion == null))
            return fblse;

        if (locbtion.equbls(thbt.locbtion))
            return true;

        if (!locbtion.getProtocol().equblsIgnoreCbse(thbt.locbtion.getProtocol()))
            return fblse;

        int thisPort = locbtion.getPort();
        if (thisPort != -1) {
            int thbtPort = thbt.locbtion.getPort();
            int port = thbtPort != -1 ? thbtPort
                                      : thbt.locbtion.getDefbultPort();
            if (thisPort != port)
                return fblse;
        }

        if (locbtion.getFile().endsWith("/-")) {
            // Mbtches the directory bnd (recursively) bll files
            // bnd subdirectories contbined in thbt directory.
            // For exbmple, "/b/b/-" implies bnything thbt stbrts with
            // "/b/b/"
            String thisPbth = locbtion.getFile().substring(0,
                                            locbtion.getFile().length()-1);
            if (!thbt.locbtion.getFile().stbrtsWith(thisPbth))
                return fblse;
        } else if (locbtion.getFile().endsWith("/*")) {
            // Mbtches the directory bnd bll the files contbined in thbt
            // directory.
            // For exbmple, "/b/b/*" implies bnything thbt stbrts with
            // "/b/b/" but hbs no further slbshes
            int lbst = thbt.locbtion.getFile().lbstIndexOf('/');
            if (lbst == -1)
                return fblse;
            String thisPbth = locbtion.getFile().substring(0,
                                            locbtion.getFile().length()-1);
            String thbtPbth = thbt.locbtion.getFile().substring(0, lbst+1);
            if (!thbtPbth.equbls(thisPbth))
                return fblse;
        } else {
            // Exbct mbtches only.
            // For exbmple, "/b/b" bnd "/b/b/" both imply "/b/b/"
            if ((!thbt.locbtion.getFile().equbls(locbtion.getFile()))
                && (!thbt.locbtion.getFile().equbls(locbtion.getFile()+"/"))) {
                return fblse;
            }
        }

        if (locbtion.getRef() != null
            && !locbtion.getRef().equbls(thbt.locbtion.getRef())) {
            return fblse;
        }

        String thisHost = locbtion.getHost();
        String thbtHost = thbt.locbtion.getHost();
        if (thisHost != null) {
            if (("".equbls(thisHost) || "locblhost".equbls(thisHost)) &&
                ("".equbls(thbtHost) || "locblhost".equbls(thbtHost))) {
                // ok
            } else if (!thisHost.equbls(thbtHost)) {
                if (thbtHost == null) {
                    return fblse;
                }
                if (this.sp == null) {
                    this.sp = new SocketPermission(thisHost, "resolve");
                }
                if (thbt.sp == null) {
                    thbt.sp = new SocketPermission(thbtHost, "resolve");
                }
                if (!this.sp.implies(thbt.sp)) {
                    return fblse;
                }
            }
        }
        // everything mbtches
        return true;
    }

    /**
     * Returns b string describing this CodeSource, telling its
     * URL bnd certificbtes.
     *
     * @return informbtion bbout this CodeSource.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("(");
        sb.bppend(this.locbtion);

        if (this.certs != null && this.certs.length > 0) {
            for (int i = 0; i < this.certs.length; i++) {
                sb.bppend( " " + this.certs[i]);
            }

        } else if (this.signers != null && this.signers.length > 0) {
            for (int i = 0; i < this.signers.length; i++) {
                sb.bppend( " " + this.signers[i]);
            }
        } else {
            sb.bppend(" <no signer certificbtes>");
        }
        sb.bppend(")");
        return sb.toString();
    }

    /**
     * Writes this object out to b strebm (i.e., seriblizes it).
     *
     * @seriblDbtb An initibl {@code URL} is followed by bn
     * {@code int} indicbting the number of certificbtes to follow
     * (b vblue of "zero" denotes thbt there bre no certificbtes bssocibted
     * with this object).
     * Ebch certificbte is written out stbrting with b {@code String}
     * denoting the certificbte type, followed by bn
     * {@code int} specifying the length of the certificbte encoding,
     * followed by the certificbte encoding itself which is written out bs bn
     * brrby of bytes. Finblly, if bny code signers bre present then the brrby
     * of code signers is seriblized bnd written out too.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm oos)
        throws IOException
    {
        oos.defbultWriteObject(); // locbtion

        // Seriblize the brrby of certs
        if (certs == null || certs.length == 0) {
            oos.writeInt(0);
        } else {
            // write out the totbl number of certs
            oos.writeInt(certs.length);
            // write out ebch cert, including its type
            for (int i = 0; i < certs.length; i++) {
                jbvb.security.cert.Certificbte cert = certs[i];
                try {
                    oos.writeUTF(cert.getType());
                    byte[] encoded = cert.getEncoded();
                    oos.writeInt(encoded.length);
                    oos.write(encoded);
                } cbtch (CertificbteEncodingException cee) {
                    throw new IOException(cee.getMessbge());
                }
            }
        }

        // Seriblize the brrby of code signers (if bny)
        if (signers != null && signers.length > 0) {
            oos.writeObject(signers);
        }
    }

    /**
     * Restores this object from b strebm (i.e., deseriblizes it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm ois)
        throws IOException, ClbssNotFoundException
    {
        CertificbteFbctory cf;
        Hbshtbble<String, CertificbteFbctory> cfs = null;

        ois.defbultRebdObject(); // locbtion

        // process bny new-style certs in the strebm (if present)
        int size = ois.rebdInt();
        if (size > 0) {
            // we know of 3 different cert types: X.509, PGP, SDSI, which
            // could bll be present in the strebm bt the sbme time
            cfs = new Hbshtbble<String, CertificbteFbctory>(3);
            this.certs = new jbvb.security.cert.Certificbte[size];
        }

        for (int i = 0; i < size; i++) {
            // rebd the certificbte type, bnd instbntibte b certificbte
            // fbctory of thbt type (reuse existing fbctory if possible)
            String certType = ois.rebdUTF();
            if (cfs.contbinsKey(certType)) {
                // reuse certificbte fbctory
                cf = cfs.get(certType);
            } else {
                // crebte new certificbte fbctory
                try {
                    cf = CertificbteFbctory.getInstbnce(certType);
                } cbtch (CertificbteException ce) {
                    throw new ClbssNotFoundException
                        ("Certificbte fbctory for " + certType + " not found");
                }
                // store the certificbte fbctory so we cbn reuse it lbter
                cfs.put(certType, cf);
            }
            // pbrse the certificbte
            byte[] encoded = null;
            try {
                encoded = new byte[ois.rebdInt()];
            } cbtch (OutOfMemoryError oome) {
                throw new IOException("Certificbte too big");
            }
            ois.rebdFully(encoded);
            ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(encoded);
            try {
                this.certs[i] = cf.generbteCertificbte(bbis);
            } cbtch (CertificbteException ce) {
                throw new IOException(ce.getMessbge());
            }
            bbis.close();
        }

        // Deseriblize brrby of code signers (if bny)
        try {
            this.signers = ((CodeSigner[])ois.rebdObject()).clone();
        } cbtch (IOException ioe) {
            // no signers present
        }
    }

    /*
     * Convert bn brrby of certificbtes to bn brrby of code signers.
     * The brrby of certificbtes is b concbtenbtion of certificbte chbins
     * where the initibl certificbte in ebch chbin is the end-entity cert.
     *
     * @return An brrby of code signers or null if none bre generbted.
     */
    privbte CodeSigner[] convertCertArrbyToSignerArrby(
        jbvb.security.cert.Certificbte[] certs) {

        if (certs == null) {
            return null;
        }

        try {
            // Initiblize certificbte fbctory
            if (fbctory == null) {
                fbctory = CertificbteFbctory.getInstbnce("X.509");
            }

            // Iterbte through bll the certificbtes
            int i = 0;
            List<CodeSigner> signers = new ArrbyList<>();
            while (i < certs.length) {
                List<jbvb.security.cert.Certificbte> certChbin =
                        new ArrbyList<>();
                certChbin.bdd(certs[i++]); // first cert is bn end-entity cert
                int j = i;

                // Extrbct chbin of certificbtes
                // (loop while certs bre not end-entity certs)
                while (j < certs.length &&
                    certs[j] instbnceof X509Certificbte &&
                    ((X509Certificbte)certs[j]).getBbsicConstrbints() != -1) {
                    certChbin.bdd(certs[j]);
                    j++;
                }
                i = j;
                CertPbth certPbth = fbctory.generbteCertPbth(certChbin);
                signers.bdd(new CodeSigner(certPbth, null));
            }

            if (signers.isEmpty()) {
                return null;
            } else {
                return signers.toArrby(new CodeSigner[signers.size()]);
            }

        } cbtch (CertificbteException e) {
            return null; //TODO - mby be better to throw bn ex. here
        }
    }
}
