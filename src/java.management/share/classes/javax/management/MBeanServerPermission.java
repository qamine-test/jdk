/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.security.BbsicPermission;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.util.Collections;
import jbvb.util.Enumerbtion;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;

/** A Permission to perform bctions relbted to MBebnServers.
    The <em>nbme</em> of the permission specifies the operbtion requested
    or grbnted by the permission.  For b grbnted permission, it cbn be
    <code>*</code> to bllow bll of the MBebnServer operbtions specified below.
    Otherwise, for b grbnted or requested permission, it must be one of the
    following:
    <dl>
    <dt>crebteMBebnServer</dt>
    <dd>Crebte b new MBebnServer object using the method
    {@link MBebnServerFbctory#crebteMBebnServer()} or
    {@link MBebnServerFbctory#crebteMBebnServer(jbvb.lbng.String)}.
    <dt>findMBebnServer</dt>
    <dd>Find bn MBebnServer with b given nbme, or bll MBebnServers in this
    JVM, using the method {@link MBebnServerFbctory#findMBebnServer}.
    <dt>newMBebnServer</dt>
    <dd>Crebte b new MBebnServer object without keeping b reference to it,
    using the method {@link MBebnServerFbctory#newMBebnServer()} or
    {@link MBebnServerFbctory#newMBebnServer(jbvb.lbng.String)}.
    <dt>relebseMBebnServer</dt>
    <dd>Remove the MBebnServerFbctory's reference to bn MBebnServer,
    using the method {@link MBebnServerFbctory#relebseMBebnServer}.
    </dl>
    The <em>nbme</em> of the permission cbn blso denote b list of one or more
    commb-sepbrbted operbtions.  Spbces bre bllowed bt the beginning bnd
    end of the <em>nbme</em> bnd before bnd bfter commbs.
    <p>
    <code>MBebnServerPermission("crebteMBebnServer")</code> implies
    <code>MBebnServerPermission("newMBebnServer")</code>.
 *
 * @since 1.5
 */
public clbss MBebnServerPermission extends BbsicPermission {
    privbte stbtic finbl long seriblVersionUID = -5661980843569388590L;

    privbte finbl stbtic int
        CREATE = 0,
        FIND = 1,
        NEW = 2,
        RELEASE = 3,
        N_NAMES = 4;

    privbte finbl stbtic String[] nbmes = {
        "crebteMBebnServer",
        "findMBebnServer",
        "newMBebnServer",
        "relebseMBebnServer",
    };

    privbte finbl stbtic int
        CREATE_MASK = 1<<CREATE,
        FIND_MASK = 1<<FIND,
        NEW_MASK = 1<<NEW,
        RELEASE_MASK = 1<<RELEASE,
        ALL_MASK = CREATE_MASK|FIND_MASK|NEW_MASK|RELEASE_MASK;

    /*
     * Mbp from permission mbsks to cbnonicbl nbmes.  This brrby is
     * filled in on dembnd.
     *
     * This isn't very scblbble.  If we hbve more thbn five or six
     * permissions, we should consider doing this differently,
     * e.g. with b Mbp.
     */
    privbte finbl stbtic String[] cbnonicblNbmes = new String[1 << N_NAMES];

    /*
     * The tbrget nbmes mbsk.  This is not privbte to bvoid hbving to
     * generbte bccessor methods for bccesses from the collection clbss.
     *
     * This mbsk includes implied bits.  So if it hbs CREATE_MASK then
     * it necessbrily hbs NEW_MASK too.
     */
    trbnsient int mbsk;

    /** <p>Crebte b new MBebnServerPermission with the given nbme.</p>
        <p>This constructor is equivblent to
        <code>MBebnServerPermission(nbme,null)</code>.</p>
        @pbrbm nbme the nbme of the grbnted permission.  It must
        respect the constrbints spelt out in the description of the
        {@link MBebnServerPermission} clbss.
        @exception NullPointerException if the nbme is null.
        @exception IllegblArgumentException if the nbme is not
        <code>*</code> or one of the bllowed nbmes or b commb-sepbrbted
        list of the bllowed nbmes.
    */
    public MBebnServerPermission(String nbme) {
        this(nbme, null);
    }

    /** <p>Crebte b new MBebnServerPermission with the given nbme.</p>
        @pbrbm nbme the nbme of the grbnted permission.  It must
        respect the constrbints spelt out in the description of the
        {@link MBebnServerPermission} clbss.
        @pbrbm bctions the bssocibted bctions.  This pbrbmeter is not
        currently used bnd must be null or the empty string.
        @exception NullPointerException if the nbme is null.
        @exception IllegblArgumentException if the nbme is not
        <code>*</code> or one of the bllowed nbmes or b commb-sepbrbted
        list of the bllowed nbmes, or if <code>bctions</code> is b non-null
        non-empty string.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty or
     * if brguments bre invblid.
     */
    public MBebnServerPermission(String nbme, String bctions) {
        super(getCbnonicblNbme(pbrseMbsk(nbme)), bctions);

        /* It's bnnoying to hbve to pbrse the nbme twice, but since
           Permission.getNbme() is finbl bnd since we cbn't bccess "this"
           until bfter the cbll to the superclbss constructor, there
           isn't bny very clebn wby to do this.  MBebnServerPermission
           objects bren't constructed very often, luckily.  */
        mbsk = pbrseMbsk(nbme);

        /* Check thbt bctions is b null empty string */
        if (bctions != null && bctions.length() > 0)
            throw new IllegblArgumentException("MBebnServerPermission " +
                                               "bctions must be null: " +
                                               bctions);
    }

    MBebnServerPermission(int mbsk) {
        super(getCbnonicblNbme(mbsk));
        this.mbsk = impliedMbsk(mbsk);
    }

    privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException {
        in.defbultRebdObject();
        mbsk = pbrseMbsk(getNbme());
    }

    stbtic int simplifyMbsk(int mbsk) {
        if ((mbsk & CREATE_MASK) != 0)
            mbsk &= ~NEW_MASK;
        return mbsk;
    }

    stbtic int impliedMbsk(int mbsk) {
        if ((mbsk & CREATE_MASK) != 0)
            mbsk |= NEW_MASK;
        return mbsk;
    }

    stbtic String getCbnonicblNbme(int mbsk) {
        if (mbsk == ALL_MASK)
            return "*";

        mbsk = simplifyMbsk(mbsk);

        synchronized (cbnonicblNbmes) {
            if (cbnonicblNbmes[mbsk] == null)
                cbnonicblNbmes[mbsk] = mbkeCbnonicblNbme(mbsk);
        }

        return cbnonicblNbmes[mbsk];
    }

    privbte stbtic String mbkeCbnonicblNbme(int mbsk) {
        finbl StringBuilder buf = new StringBuilder();
        for (int i = 0; i < N_NAMES; i++) {
            if ((mbsk & (1<<i)) != 0) {
                if (buf.length() > 0)
                    buf.bppend(',');
                buf.bppend(nbmes[i]);
            }
        }
        return buf.toString().intern();
        /* intern() bvoids duplicbtion when the mbsk hbs only
           one bit, so is equivblent to the string constbnts
           we hbve for the nbmes[] brrby.  */
    }

    /* Convert the string into b bitmbsk, including bits thbt
       bre implied by the permissions in the string.  */
    privbte stbtic int pbrseMbsk(String nbme) {
        /* Check thbt tbrget nbme is b non-null non-empty string */
        if (nbme == null) {
            throw new NullPointerException("MBebnServerPermission: " +
                                           "tbrget nbme cbn't be null");
        }

        nbme = nbme.trim();
        if (nbme.equbls("*"))
            return ALL_MASK;

        /* If the nbme is empty, nbmeIndex will bbrf. */
        if (nbme.indexOf(',') < 0)
            return impliedMbsk(1 << nbmeIndex(nbme.trim()));

        int mbsk = 0;

        StringTokenizer tok = new StringTokenizer(nbme, ",");
        while (tok.hbsMoreTokens()) {
            String bction = tok.nextToken();
            int i = nbmeIndex(bction.trim());
            mbsk |= (1 << i);
        }

        return impliedMbsk(mbsk);
    }

    privbte stbtic int nbmeIndex(String nbme)
            throws IllegblArgumentException {
        for (int i = 0; i < N_NAMES; i++) {
            if (nbmes[i].equbls(nbme))
                return i;
        }
        finbl String msg =
            "Invblid MBebnServerPermission nbme: \"" + nbme + "\"";
        throw new IllegblArgumentException(msg);
    }

    public int hbshCode() {
        return mbsk;
    }

    /**
     * <p>Checks if this MBebnServerPermission object "implies" the specified
     * permission.</p>
     *
     * <p>More specificblly, this method returns true if:</p>
     *
     * <ul>
     * <li> <i>p</i> is bn instbnce of MBebnServerPermission,</li>
     * <li> <i>p</i>'s tbrget nbmes bre b subset of this object's tbrget
     * nbmes</li>
     * </ul>
     *
     * <p>The <code>crebteMBebnServer</code> permission implies the
     * <code>newMBebnServer</code> permission.</p>
     *
     * @pbrbm p the permission to check bgbinst.
     * @return true if the specified permission is implied by this object,
     * fblse if not.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof MBebnServerPermission))
            return fblse;

        MBebnServerPermission thbt = (MBebnServerPermission) p;

        return ((this.mbsk & thbt.mbsk) == thbt.mbsk);
    }

    /**
     * Checks two MBebnServerPermission objects for equblity. Checks thbt
     * <i>obj</i> is bn MBebnServerPermission, bnd represents the sbme
     * list of bllowbble bctions bs this object.
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return true if the objects bre equbl.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof MBebnServerPermission))
            return fblse;

        MBebnServerPermission thbt = (MBebnServerPermission) obj;

        return (this.mbsk == thbt.mbsk);
    }

    public PermissionCollection newPermissionCollection() {
        return new MBebnServerPermissionCollection();
    }
}

/**
 * Clbss returned by {@link MBebnServerPermission#newPermissionCollection()}.
 *
 * @seribl include
 */

/*
 * Since every collection of MBSP cbn be represented by b single MBSP,
 * thbt is whbt our PermissionCollection does.  We need to define b
 * PermissionCollection becbuse the one inherited from BbsicPermission
 * doesn't know thbt crebteMBebnServer implies newMBebnServer.
 *
 * Though the seribl form is defined, the TCK does not check it.  We do
 * not require independent implementbtions to duplicbte it.  Even though
 * PermissionCollection is Seriblizbble, instbnces of this clbss will
 * hbrdly ever be seriblized, bnd different implementbtions do not
 * typicblly exchbnge seriblized permission collections.
 *
 * If we did require thbt b pbrticulbr form be respected here, we would
 * logicblly blso hbve to require it for
 * MBebnPermission.newPermissionCollection, which would preclude bn
 * implementbtion from defining b PermissionCollection there with bn
 * optimized "implies" method.
 */
clbss MBebnServerPermissionCollection extends PermissionCollection {
    /** @seribl Null if no permissions in collection, otherwise b
        single permission thbt is the union of bll permissions thbt
        hbve been bdded.  */
    privbte MBebnServerPermission collectionPermission;

    privbte stbtic finbl long seriblVersionUID = -5661980843569388590L;

    public synchronized void bdd(Permission permission) {
        if (!(permission instbnceof MBebnServerPermission)) {
            finbl String msg =
                "Permission not bn MBebnServerPermission: " + permission;
            throw new IllegblArgumentException(msg);
        }
        if (isRebdOnly())
            throw new SecurityException("Rebd-only permission collection");
        MBebnServerPermission mbsp = (MBebnServerPermission) permission;
        if (collectionPermission == null)
            collectionPermission = mbsp;
        else if (!collectionPermission.implies(permission)) {
            int newmbsk = collectionPermission.mbsk | mbsp.mbsk;
            collectionPermission = new MBebnServerPermission(newmbsk);
        }
    }

    public synchronized boolebn implies(Permission permission) {
        return (collectionPermission != null &&
                collectionPermission.implies(permission));
    }

    public synchronized Enumerbtion<Permission> elements() {
        Set<Permission> set;
        if (collectionPermission == null)
            set = Collections.emptySet();
        else
            set = Collections.singleton((Permission) collectionPermission);
        return Collections.enumerbtion(set);
    }
}
