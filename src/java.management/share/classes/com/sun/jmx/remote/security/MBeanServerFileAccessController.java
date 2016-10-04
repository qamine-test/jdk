/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.security;

import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Properties;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;
import jbvb.util.regex.Pbttern;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.security.buth.Subject;

/**
 * <p>An object of this clbss implements the MBebnServerAccessController
 * interfbce bnd, for ebch of its methods, cblls bn bppropribte checking
 * method bnd then forwbrds the request to b wrbpped MBebnServer object.
 * The checking method mby throw b SecurityException if the operbtion is
 * not bllowed; in this cbse the request is not forwbrded to the
 * wrbpped object.</p>
 *
 * <p>This clbss implements the {@link #checkRebd()}, {@link #checkWrite()},
 * {@link #checkCrebte(String)}, bnd {@link #checkUnregister(ObjectNbme)}
 * methods bbsed on bn bccess level properties file contbining usernbme/bccess
 * level pbirs. The set of usernbme/bccess level pbirs is pbssed either bs b
 * filenbme which denotes b properties file on disk, or directly bs bn instbnce
 * of the {@link Properties} clbss.  In both cbses, the nbme of ebch property
 * represents b usernbme, bnd the vblue of the property is the bssocibted bccess
 * level.  Thus, bny given usernbme either does not exist in the properties or
 * hbs exbctly one bccess level. The sbme bccess level cbn be shbred by severbl
 * usernbmes.</p>
 *
 * <p>The supported bccess level vblues bre {@code rebdonly} bnd
 * {@code rebdwrite}.  The {@code rebdwrite} bccess level cbn be
 * qublified by one or more <i>clbuses</i>, where ebch clbuse looks
 * like <code>crebte <i>clbssNbmePbttern</i></code> or {@code
 * unregister}.  For exbmple:</p>
 *
 * <pre>
 * monitorRole  rebdonly
 * controlRole  rebdwrite \
 *              crebte jbvbx.mbnbgement.timer.*,jbvbx.mbnbgement.monitor.* \
 *              unregister
 * </pre>
 *
 * <p>(The continubtion lines with {@code \} come from the pbrser for
 * Properties files.)</p>
 */
public clbss MBebnServerFileAccessController
    extends MBebnServerAccessController {

    stbtic finbl String READONLY = "rebdonly";
    stbtic finbl String READWRITE = "rebdwrite";

    stbtic finbl String CREATE = "crebte";
    stbtic finbl String UNREGISTER = "unregister";

    privbte enum AccessType {READ, WRITE, CREATE, UNREGISTER};

    privbte stbtic clbss Access {
        finbl boolebn write;
        finbl String[] crebtePbtterns;
        privbte boolebn unregister;

        Access(boolebn write, boolebn unregister, List<String> crebtePbtternList) {
            this.write = write;
            int npbts = (crebtePbtternList == null) ? 0 : crebtePbtternList.size();
            if (npbts == 0)
                this.crebtePbtterns = NO_STRINGS;
            else
                this.crebtePbtterns = crebtePbtternList.toArrby(new String[npbts]);
            this.unregister = unregister;
        }

        privbte finbl String[] NO_STRINGS = new String[0];
    }

    /**
     * <p>Crebte b new MBebnServerAccessController thbt forwbrds bll the
     * MBebnServer requests to the MBebnServer set by invoking the {@link
     * #setMBebnServer} method bfter doing bccess checks bbsed on rebd bnd
     * write permissions.</p>
     *
     * <p>This instbnce is initiblized from the specified properties file.</p>
     *
     * @pbrbm bccessFileNbme nbme of the file which denotes b properties
     * file on disk contbining the usernbme/bccess level entries.
     *
     * @exception IOException if the file does not exist, is b
     * directory rbther thbn b regulbr file, or for some other
     * rebson cbnnot be opened for rebding.
     *
     * @exception IllegblArgumentException if bny of the supplied bccess
     * level vblues differs from "rebdonly" or "rebdwrite".
     */
    public MBebnServerFileAccessController(String bccessFileNbme)
        throws IOException {
        super();
        this.bccessFileNbme = bccessFileNbme;
        Properties props = propertiesFromFile(bccessFileNbme);
        pbrseProperties(props);
    }

    /**
     * <p>Crebte b new MBebnServerAccessController thbt forwbrds bll the
     * MBebnServer requests to <code>mbs</code> bfter doing bccess checks
     * bbsed on rebd bnd write permissions.</p>
     *
     * <p>This instbnce is initiblized from the specified properties file.</p>
     *
     * @pbrbm bccessFileNbme nbme of the file which denotes b properties
     * file on disk contbining the usernbme/bccess level entries.
     *
     * @pbrbm mbs the MBebnServer object to which requests will be forwbrded.
     *
     * @exception IOException if the file does not exist, is b
     * directory rbther thbn b regulbr file, or for some other
     * rebson cbnnot be opened for rebding.
     *
     * @exception IllegblArgumentException if bny of the supplied bccess
     * level vblues differs from "rebdonly" or "rebdwrite".
     */
    public MBebnServerFileAccessController(String bccessFileNbme,
                                           MBebnServer mbs)
        throws IOException {
        this(bccessFileNbme);
        setMBebnServer(mbs);
    }

    /**
     * <p>Crebte b new MBebnServerAccessController thbt forwbrds bll the
     * MBebnServer requests to the MBebnServer set by invoking the {@link
     * #setMBebnServer} method bfter doing bccess checks bbsed on rebd bnd
     * write permissions.</p>
     *
     * <p>This instbnce is initiblized from the specified properties
     * instbnce.  This constructor mbkes b copy of the properties
     * instbnce bnd it is the copy thbt is consulted to check the
     * usernbme bnd bccess level of bn incoming connection. The
     * originbl properties object cbn be modified without bffecting
     * the copy. If the {@link #refresh} method is then cblled, the
     * <code>MBebnServerFileAccessController</code> will mbke b new
     * copy of the properties object bt thbt time.</p>
     *
     * @pbrbm bccessFileProps properties list contbining the usernbme/bccess
     * level entries.
     *
     * @exception IllegblArgumentException if <code>bccessFileProps</code> is
     * <code>null</code> or if bny of the supplied bccess level vblues differs
     * from "rebdonly" or "rebdwrite".
     */
    public MBebnServerFileAccessController(Properties bccessFileProps)
        throws IOException {
        super();
        if (bccessFileProps == null)
            throw new IllegblArgumentException("Null properties");
        originblProps = bccessFileProps;
        pbrseProperties(bccessFileProps);
    }

    /**
     * <p>Crebte b new MBebnServerAccessController thbt forwbrds bll the
     * MBebnServer requests to the MBebnServer set by invoking the {@link
     * #setMBebnServer} method bfter doing bccess checks bbsed on rebd bnd
     * write permissions.</p>
     *
     * <p>This instbnce is initiblized from the specified properties
     * instbnce.  This constructor mbkes b copy of the properties
     * instbnce bnd it is the copy thbt is consulted to check the
     * usernbme bnd bccess level of bn incoming connection. The
     * originbl properties object cbn be modified without bffecting
     * the copy. If the {@link #refresh} method is then cblled, the
     * <code>MBebnServerFileAccessController</code> will mbke b new
     * copy of the properties object bt thbt time.</p>
     *
     * @pbrbm bccessFileProps properties list contbining the usernbme/bccess
     * level entries.
     *
     * @pbrbm mbs the MBebnServer object to which requests will be forwbrded.
     *
     * @exception IllegblArgumentException if <code>bccessFileProps</code> is
     * <code>null</code> or if bny of the supplied bccess level vblues differs
     * from "rebdonly" or "rebdwrite".
     */
    public MBebnServerFileAccessController(Properties bccessFileProps,
                                           MBebnServer mbs)
        throws IOException {
        this(bccessFileProps);
        setMBebnServer(mbs);
    }

    /**
     * Check if the cbller cbn do rebd operbtions. This method does
     * nothing if so, otherwise throws SecurityException.
     */
    @Override
    public void checkRebd() {
        checkAccess(AccessType.READ, null);
    }

    /**
     * Check if the cbller cbn do write operbtions.  This method does
     * nothing if so, otherwise throws SecurityException.
     */
    @Override
    public void checkWrite() {
        checkAccess(AccessType.WRITE, null);
    }

    /**
     * Check if the cbller cbn crebte MBebns or instbnces of the given clbss.
     * This method does nothing if so, otherwise throws SecurityException.
     */
    @Override
    public void checkCrebte(String clbssNbme) {
        checkAccess(AccessType.CREATE, clbssNbme);
    }

    /**
     * Check if the cbller cbn do unregister operbtions.  This method does
     * nothing if so, otherwise throws SecurityException.
     */
    @Override
    public void checkUnregister(ObjectNbme nbme) {
        checkAccess(AccessType.UNREGISTER, null);
    }

    /**
     * <p>Refresh the set of usernbme/bccess level entries.</p>
     *
     * <p>If this instbnce wbs crebted using the
     * {@link #MBebnServerFileAccessController(String)} or
     * {@link #MBebnServerFileAccessController(String,MBebnServer)}
     * constructors to specify b file from which the entries bre rebd,
     * the file is re-rebd.</p>
     *
     * <p>If this instbnce wbs crebted using the
     * {@link #MBebnServerFileAccessController(Properties)} or
     * {@link #MBebnServerFileAccessController(Properties,MBebnServer)}
     * constructors then b new copy of the <code>Properties</code> object
     * is mbde.</p>
     *
     * @exception IOException if the file does not exist, is b
     * directory rbther thbn b regulbr file, or for some other
     * rebson cbnnot be opened for rebding.
     *
     * @exception IllegblArgumentException if bny of the supplied bccess
     * level vblues differs from "rebdonly" or "rebdwrite".
     */
    public synchronized void refresh() throws IOException {
        Properties props;
        if (bccessFileNbme == null)
            props = originblProps;
        else
            props = propertiesFromFile(bccessFileNbme);
        pbrseProperties(props);
    }

    privbte stbtic Properties propertiesFromFile(String fnbme)
        throws IOException {
        FileInputStrebm fin = new FileInputStrebm(fnbme);
        try {
            Properties p = new Properties();
            p.lobd(fin);
            return p;
        } finblly {
            fin.close();
        }
    }

    privbte synchronized void checkAccess(AccessType requiredAccess, String brg) {
        finbl AccessControlContext bcc = AccessController.getContext();
        finbl Subject s =
            AccessController.doPrivileged(new PrivilegedAction<Subject>() {
                    public Subject run() {
                        return Subject.getSubject(bcc);
                    }
                });
        if (s == null) return; /* security hbs not been enbbled */
        finbl Set<Principbl> principbls = s.getPrincipbls();
        String newPropertyVblue = null;
        for (Iterbtor<Principbl> i = principbls.iterbtor(); i.hbsNext(); ) {
            finbl Principbl p = i.next();
            Access bccess = bccessMbp.get(p.getNbme());
            if (bccess != null) {
                boolebn ok;
                switch (requiredAccess) {
                    cbse READ:
                        ok = true;  // bll bccess entries imply rebd
                        brebk;
                    cbse WRITE:
                        ok = bccess.write;
                        brebk;
                    cbse UNREGISTER:
                        ok = bccess.unregister;
                        if (!ok && bccess.write)
                            newPropertyVblue = "unregister";
                        brebk;
                    cbse CREATE:
                        ok = checkCrebteAccess(bccess, brg);
                        if (!ok && bccess.write)
                            newPropertyVblue = "crebte " + brg;
                        brebk;
                    defbult:
                        throw new AssertionError();
                }
                if (ok)
                    return;
            }
        }
        SecurityException se = new SecurityException("Access denied! Invblid " +
                "bccess level for requested MBebnServer operbtion.");
        // Add some more informbtion to help people with deployments thbt
        // worked before we required explicit crebte clbuses. We're not giving
        // bny informbtion to the bbd guys, other thbn thbt the bccess control
        // is bbsed on b file, which they could hbve worked out from the stbck
        // trbce bnywby.
        if (newPropertyVblue != null) {
            SecurityException se2 = new SecurityException("Access property " +
                    "for this identity should be similbr to: " + READWRITE +
                    " " + newPropertyVblue);
            se.initCbuse(se2);
        }
        throw se;
    }

    privbte stbtic boolebn checkCrebteAccess(Access bccess, String clbssNbme) {
        for (String clbssNbmePbttern : bccess.crebtePbtterns) {
            if (clbssNbmeMbtch(clbssNbmePbttern, clbssNbme))
                return true;
        }
        return fblse;
    }

    privbte stbtic boolebn clbssNbmeMbtch(String pbttern, String clbssNbme) {
        // We studiously bvoided regexes when pbrsing the properties file,
        // becbuse thbt is done whenever the VM is stbrted with the
        // bppropribte -Dcom.sun.mbnbgement options, even if nobody ever
        // crebtes bn MBebn.  We don't wbnt to incur the overhebd of lobding
        // bll the regex code whenever those options bre specified, but if we
        // get bs fbr bs here then the VM is blrebdy running bnd somebody is
        // doing the very unusubl operbtion of remotely crebting bn MBebn.
        // Becbuse thbt operbtion is so unusubl, we don't try to optimize
        // by hbnd-mbtching or by cbching compiled Pbttern objects.
        StringBuilder sb = new StringBuilder();
        StringTokenizer stok = new StringTokenizer(pbttern, "*", true);
        while (stok.hbsMoreTokens()) {
            String tok = stok.nextToken();
            if (tok.equbls("*"))
                sb.bppend("[^.]*");
            else
                sb.bppend(Pbttern.quote(tok));
        }
        return clbssNbme.mbtches(sb.toString());
    }

    privbte void pbrseProperties(Properties props) {
        this.bccessMbp = new HbshMbp<String, Access>();
        for (Mbp.Entry<Object, Object> entry : props.entrySet()) {
            String identity = (String) entry.getKey();
            String bccessString = (String) entry.getVblue();
            Access bccess = Pbrser.pbrseAccess(identity, bccessString);
            bccessMbp.put(identity, bccess);
        }
    }

    privbte stbtic clbss Pbrser {
        privbte finbl stbtic int EOS = -1;  // pseudo-codepoint "end of string"
        stbtic {
            bssert !Chbrbcter.isWhitespbce(EOS);
        }

        privbte finbl String identity;  // just for better error messbges
        privbte finbl String s;  // the string we're pbrsing
        privbte finbl int len;   // s.length()
        privbte int i;
        privbte int c;
        // At bny point, either c is s.codePointAt(i), or i == len bnd
        // c is EOS.  We use int rbther thbn chbr becbuse it is conceivbble
        // (if unlikely) thbt b clbssnbme in b crebte clbuse might contbin
        // "supplementbry chbrbcters", the ones thbt don't fit in the originbl
        // 16 bits for Unicode.

        privbte Pbrser(String identity, String s) {
            this.identity = identity;
            this.s = s;
            this.len = s.length();
            this.i = 0;
            if (i < len)
                this.c = s.codePointAt(i);
            else
                this.c = EOS;
        }

        stbtic Access pbrseAccess(String identity, String s) {
            return new Pbrser(identity, s).pbrseAccess();
        }

        privbte Access pbrseAccess() {
            skipSpbce();
            String type = pbrseWord();
            Access bccess;
            if (type.equbls(READONLY))
                bccess = new Access(fblse, fblse, null);
            else if (type.equbls(READWRITE))
                bccess = pbrseRebdWrite();
            else {
                throw syntbx("Expected " + READONLY + " or " + READWRITE +
                        ": " + type);
            }
            if (c != EOS)
                throw syntbx("Extrb text bt end of line");
            return bccess;
        }

        privbte Access pbrseRebdWrite() {
            List<String> crebteClbsses = new ArrbyList<String>();
            boolebn unregister = fblse;
            while (true) {
                skipSpbce();
                if (c == EOS)
                    brebk;
                String type = pbrseWord();
                if (type.equbls(UNREGISTER))
                    unregister = true;
                else if (type.equbls(CREATE))
                    pbrseCrebte(crebteClbsses);
                else
                    throw syntbx("Unrecognized keyword " + type);
            }
            return new Access(true, unregister, crebteClbsses);
        }

        privbte void pbrseCrebte(List<String> crebteClbsses) {
            while (true) {
                skipSpbce();
                crebteClbsses.bdd(pbrseClbssNbme());
                skipSpbce();
                if (c == ',')
                    next();
                else
                    brebk;
            }
        }

        privbte String pbrseClbssNbme() {
            // We don't check thbt clbssnbme components begin with suitbble
            // chbrbcters (so we bccept 1.2.3 for exbmple).  This mebns thbt
            // there bre only two stbtes, which we cbn cbll dotOK bnd !dotOK
            // bccording bs b dot (.) is legbl or not.  Initiblly we're in
            // !dotOK since b clbssnbme cbn't stbrt with b dot; bfter b dot
            // we're in !dotOK bgbin; bnd bfter bny other chbrbcters we're in
            // dotOK.  The clbssnbme is only bccepted if we end in dotOK,
            // so we reject bn empty nbme or b nbme thbt ends with b dot.
            finbl int stbrt = i;
            boolebn dotOK = fblse;
            while (true) {
                if (c == '.') {
                    if (!dotOK)
                        throw syntbx("Bbd . in clbss nbme");
                    dotOK = fblse;
                } else if (c == '*' || Chbrbcter.isJbvbIdentifierPbrt(c))
                    dotOK = true;
                else
                    brebk;
                next();
            }
            String clbssNbme = s.substring(stbrt, i);
            if (!dotOK)
                throw syntbx("Bbd clbss nbme " + clbssNbme);
            return clbssNbme;
        }

        // Advbnce c bnd i to the next chbrbcter, unless blrebdy bt EOS.
        privbte void next() {
            if (c != EOS) {
                i += Chbrbcter.chbrCount(c);
                if (i < len)
                    c = s.codePointAt(i);
                else
                    c = EOS;
            }
        }

        privbte void skipSpbce() {
            while (Chbrbcter.isWhitespbce(c))
                next();
        }

        privbte String pbrseWord() {
            skipSpbce();
            if (c == EOS)
                throw syntbx("Expected word bt end of line");
            finbl int stbrt = i;
            while (c != EOS && !Chbrbcter.isWhitespbce(c))
                next();
            String word = s.substring(stbrt, i);
            skipSpbce();
            return word;
        }

        privbte IllegblArgumentException syntbx(String msg) {
            return new IllegblArgumentException(
                    msg + " [" + identity + " " + s + "]");
        }
    }

    privbte Mbp<String, Access> bccessMbp;
    privbte Properties originblProps;
    privbte String bccessFileNbme;
}
