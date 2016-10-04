/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.lbng.reflect.*;
import jbvb.text.MessbgeFormbt;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.DombinCombiner;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.security.Principbl;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.ProtectionDombin;
import sun.security.util.ResourcesMgr;

/**
 * <p> A {@code Subject} represents b grouping of relbted informbtion
 * for b single entity, such bs b person.
 * Such informbtion includes the Subject's identities bs well bs
 * its security-relbted bttributes
 * (pbsswords bnd cryptogrbphic keys, for exbmple).
 *
 * <p> Subjects mby potentiblly hbve multiple identities.
 * Ebch identity is represented bs b {@code Principbl}
 * within the {@code Subject}.  Principbls simply bind nbmes to b
 * {@code Subject}.  For exbmple, b {@code Subject} thbt hbppens
 * to be b person, Alice, might hbve two Principbls:
 * one which binds "Alice Bbr", the nbme on her driver license,
 * to the {@code Subject}, bnd bnother which binds,
 * "999-99-9999", the number on her student identificbtion cbrd,
 * to the {@code Subject}.  Both Principbls refer to the sbme
 * {@code Subject} even though ebch hbs b different nbme.
 *
 * <p> A {@code Subject} mby blso own security-relbted bttributes,
 * which bre referred to bs credentibls.
 * Sensitive credentibls thbt require specibl protection, such bs
 * privbte cryptogrbphic keys, bre stored within b privbte credentibl
 * {@code Set}.  Credentibls intended to be shbred, such bs
 * public key certificbtes or Kerberos server tickets bre stored
 * within b public credentibl {@code Set}.  Different permissions
 * bre required to bccess bnd modify the different credentibl Sets.
 *
 * <p> To retrieve bll the Principbls bssocibted with b {@code Subject},
 * invoke the {@code getPrincipbls} method.  To retrieve
 * bll the public or privbte credentibls belonging to b {@code Subject},
 * invoke the {@code getPublicCredentibls} method or
 * {@code getPrivbteCredentibls} method, respectively.
 * To modify the returned {@code Set} of Principbls bnd credentibls,
 * use the methods defined in the {@code Set} clbss.
 * For exbmple:
 * <pre>
 *      Subject subject;
 *      Principbl principbl;
 *      Object credentibl;
 *
 *      // bdd b Principbl bnd credentibl to the Subject
 *      subject.getPrincipbls().bdd(principbl);
 *      subject.getPublicCredentibls().bdd(credentibl);
 * </pre>
 *
 * <p> This {@code Subject} clbss implements {@code Seriblizbble}.
 * While the Principbls bssocibted with the {@code Subject} bre seriblized,
 * the credentibls bssocibted with the {@code Subject} bre not.
 * Note thbt the {@code jbvb.security.Principbl} clbss
 * does not implement {@code Seriblizbble}.  Therefore bll concrete
 * {@code Principbl} implementbtions bssocibted with Subjects
 * must implement {@code Seriblizbble}.
 *
 * @see jbvb.security.Principbl
 * @see jbvb.security.DombinCombiner
 */
public finbl clbss Subject implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -8308522755600156056L;

    /**
     * A {@code Set} thbt provides b view of bll of this
     * Subject's Principbls
     *
     * <p>
     *
     * @seribl Ebch element in this set is b
     *          {@code jbvb.security.Principbl}.
     *          The set is b {@code Subject.SecureSet}.
     */
    Set<Principbl> principbls;

    /**
     * Sets thbt provide b view of bll of this
     * Subject's Credentibls
     */
    trbnsient Set<Object> pubCredentibls;
    trbnsient Set<Object> privCredentibls;

    /**
     * Whether this Subject is rebd-only
     *
     * @seribl
     */
    privbte volbtile boolebn rebdOnly = fblse;

    privbte stbtic finbl int PRINCIPAL_SET = 1;
    privbte stbtic finbl int PUB_CREDENTIAL_SET = 2;
    privbte stbtic finbl int PRIV_CREDENTIAL_SET = 3;

    privbte stbtic finbl ProtectionDombin[] NULL_PD_ARRAY
        = new ProtectionDombin[0];

    /**
     * Crebte bn instbnce of b {@code Subject}
     * with bn empty {@code Set} of Principbls bnd empty
     * Sets of public bnd privbte credentibls.
     *
     * <p> The newly constructed Sets check whether this {@code Subject}
     * hbs been set rebd-only before permitting subsequent modificbtions.
     * The newly crebted Sets blso prevent illegbl modificbtions
     * by ensuring thbt cbllers hbve sufficient permissions.  These Sets
     * blso prohibit null elements, bnd bttempts to bdd or query b null
     * element will result in b {@code NullPointerException}.
     *
     * <p> To modify the Principbls Set, the cbller must hbve
     * {@code AuthPermission("modifyPrincipbls")}.
     * To modify the public credentibl Set, the cbller must hbve
     * {@code AuthPermission("modifyPublicCredentibls")}.
     * To modify the privbte credentibl Set, the cbller must hbve
     * {@code AuthPermission("modifyPrivbteCredentibls")}.
     */
    public Subject() {

        this.principbls = Collections.synchronizedSet
                        (new SecureSet<Principbl>(this, PRINCIPAL_SET));
        this.pubCredentibls = Collections.synchronizedSet
                        (new SecureSet<Object>(this, PUB_CREDENTIAL_SET));
        this.privCredentibls = Collections.synchronizedSet
                        (new SecureSet<Object>(this, PRIV_CREDENTIAL_SET));
    }

    /**
     * Crebte bn instbnce of b {@code Subject} with
     * Principbls bnd credentibls.
     *
     * <p> The Principbls bnd credentibls from the specified Sets
     * bre copied into newly constructed Sets.
     * These newly crebted Sets check whether this {@code Subject}
     * hbs been set rebd-only before permitting subsequent modificbtions.
     * The newly crebted Sets blso prevent illegbl modificbtions
     * by ensuring thbt cbllers hbve sufficient permissions.  These Sets
     * blso prohibit null elements, bnd bttempts to bdd or query b null
     * element will result in b {@code NullPointerException}.
     *
     * <p> To modify the Principbls Set, the cbller must hbve
     * {@code AuthPermission("modifyPrincipbls")}.
     * To modify the public credentibl Set, the cbller must hbve
     * {@code AuthPermission("modifyPublicCredentibls")}.
     * To modify the privbte credentibl Set, the cbller must hbve
     * {@code AuthPermission("modifyPrivbteCredentibls")}.
     * <p>
     *
     * @pbrbm rebdOnly true if the {@code Subject} is to be rebd-only,
     *          bnd fblse otherwise. <p>
     *
     * @pbrbm principbls the {@code Set} of Principbls
     *          to be bssocibted with this {@code Subject}. <p>
     *
     * @pbrbm pubCredentibls the {@code Set} of public credentibls
     *          to be bssocibted with this {@code Subject}. <p>
     *
     * @pbrbm privCredentibls the {@code Set} of privbte credentibls
     *          to be bssocibted with this {@code Subject}.
     *
     * @exception NullPointerException if the specified
     *          {@code principbls}, {@code pubCredentibls},
     *          or {@code privCredentibls} bre {@code null},
     *          or b null vblue exists within bny of these three
     *          Sets.
     */
    public Subject(boolebn rebdOnly, Set<? extends Principbl> principbls,
                   Set<?> pubCredentibls, Set<?> privCredentibls)
    {
        collectionNullClebn(principbls);
        collectionNullClebn(pubCredentibls);
        collectionNullClebn(privCredentibls);

        this.principbls = Collections.synchronizedSet(new SecureSet<Principbl>
                                (this, PRINCIPAL_SET, principbls));
        this.pubCredentibls = Collections.synchronizedSet(new SecureSet<Object>
                                (this, PUB_CREDENTIAL_SET, pubCredentibls));
        this.privCredentibls = Collections.synchronizedSet(new SecureSet<Object>
                                (this, PRIV_CREDENTIAL_SET, privCredentibls));
        this.rebdOnly = rebdOnly;
    }

    /**
     * Set this {@code Subject} to be rebd-only.
     *
     * <p> Modificbtions (bdditions bnd removbls) to this Subject's
     * {@code Principbl} {@code Set} bnd
     * credentibl Sets will be disbllowed.
     * The {@code destroy} operbtion on this Subject's credentibls will
     * still be permitted.
     *
     * <p> Subsequent bttempts to modify the Subject's {@code Principbl}
     * bnd credentibl Sets will result in bn
     * {@code IllegblStbteException} being thrown.
     * Also, once b {@code Subject} is rebd-only,
     * it cbn not be reset to being writbble bgbin.
     *
     * <p>
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to set this {@code Subject} to be rebd-only.
     */
    public void setRebdOnly() {
        jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(AuthPermissionHolder.SET_READ_ONLY_PERMISSION);
        }

        this.rebdOnly = true;
    }

    /**
     * Query whether this {@code Subject} is rebd-only.
     *
     * <p>
     *
     * @return true if this {@code Subject} is rebd-only, fblse otherwise.
     */
    public boolebn isRebdOnly() {
        return this.rebdOnly;
    }

    /**
     * Get the {@code Subject} bssocibted with the provided
     * {@code AccessControlContext}.
     *
     * <p> The {@code AccessControlContext} mby contbin mbny
     * Subjects (from nested {@code doAs} cblls).
     * In this situbtion, the most recent {@code Subject} bssocibted
     * with the {@code AccessControlContext} is returned.
     *
     * <p>
     *
     * @pbrbm  bcc the {@code AccessControlContext} from which to retrieve
     *          the {@code Subject}.
     *
     * @return  the {@code Subject} bssocibted with the provided
     *          {@code AccessControlContext}, or {@code null}
     *          if no {@code Subject} is bssocibted
     *          with the provided {@code AccessControlContext}.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get the {@code Subject}. <p>
     *
     * @exception NullPointerException if the provided
     *          {@code AccessControlContext} is {@code null}.
     */
    public stbtic Subject getSubject(finbl AccessControlContext bcc) {

        jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(AuthPermissionHolder.GET_SUBJECT_PERMISSION);
        }

        Objects.requireNonNull(bcc, ResourcesMgr.getString
                ("invblid.null.AccessControlContext.provided"));

        // return the Subject from the DombinCombiner of the provided context
        return AccessController.doPrivileged
            (new jbvb.security.PrivilegedAction<Subject>() {
            public Subject run() {
                DombinCombiner dc = bcc.getDombinCombiner();
                if (!(dc instbnceof SubjectDombinCombiner)) {
                    return null;
                }
                SubjectDombinCombiner sdc = (SubjectDombinCombiner)dc;
                return sdc.getSubject();
            }
        });
    }

    /**
     * Perform work bs b pbrticulbr {@code Subject}.
     *
     * <p> This method first retrieves the current Threbd's
     * {@code AccessControlContext} vib
     * {@code AccessController.getContext},
     * bnd then instbntibtes b new {@code AccessControlContext}
     * using the retrieved context blong with b new
     * {@code SubjectDombinCombiner} (constructed using
     * the provided {@code Subject}).
     * Finblly, this method invokes {@code AccessController.doPrivileged},
     * pbssing it the provided {@code PrivilegedAction},
     * bs well bs the newly constructed {@code AccessControlContext}.
     *
     * <p>
     *
     * @pbrbm subject the {@code Subject} thbt the specified
     *                  {@code bction} will run bs.  This pbrbmeter
     *                  mby be {@code null}. <p>
     *
     * @pbrbm <T> the type of the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     *
     * @pbrbm bction the code to be run bs the specified
     *                  {@code Subject}. <p>
     *
     * @return the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     *
     * @exception NullPointerException if the {@code PrivilegedAction}
     *                  is {@code null}. <p>
     *
     * @exception SecurityException if the cbller does not hbve permission
     *                  to invoke this method.
     */
    public stbtic <T> T doAs(finbl Subject subject,
                        finbl jbvb.security.PrivilegedAction<T> bction) {

        jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(AuthPermissionHolder.DO_AS_PERMISSION);
        }

        Objects.requireNonNull(bction,
                ResourcesMgr.getString("invblid.null.bction.provided"));

        // set up the new Subject-bbsed AccessControlContext
        // for doPrivileged
        finbl AccessControlContext currentAcc = AccessController.getContext();

        // cbll doPrivileged bnd push this new context on the stbck
        return jbvb.security.AccessController.doPrivileged
                                        (bction,
                                        crebteContext(subject, currentAcc));
    }

    /**
     * Perform work bs b pbrticulbr {@code Subject}.
     *
     * <p> This method first retrieves the current Threbd's
     * {@code AccessControlContext} vib
     * {@code AccessController.getContext},
     * bnd then instbntibtes b new {@code AccessControlContext}
     * using the retrieved context blong with b new
     * {@code SubjectDombinCombiner} (constructed using
     * the provided {@code Subject}).
     * Finblly, this method invokes {@code AccessController.doPrivileged},
     * pbssing it the provided {@code PrivilegedExceptionAction},
     * bs well bs the newly constructed {@code AccessControlContext}.
     *
     * <p>
     *
     * @pbrbm subject the {@code Subject} thbt the specified
     *                  {@code bction} will run bs.  This pbrbmeter
     *                  mby be {@code null}. <p>
     *
     * @pbrbm <T> the type of the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     *
     * @pbrbm bction the code to be run bs the specified
     *                  {@code Subject}. <p>
     *
     * @return the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     *
     * @exception PrivilegedActionException if the
     *                  {@code PrivilegedExceptionAction.run}
     *                  method throws b checked exception. <p>
     *
     * @exception NullPointerException if the specified
     *                  {@code PrivilegedExceptionAction} is
     *                  {@code null}. <p>
     *
     * @exception SecurityException if the cbller does not hbve permission
     *                  to invoke this method.
     */
    public stbtic <T> T doAs(finbl Subject subject,
                        finbl jbvb.security.PrivilegedExceptionAction<T> bction)
                        throws jbvb.security.PrivilegedActionException {

        jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(AuthPermissionHolder.DO_AS_PERMISSION);
        }

        Objects.requireNonNull(bction,
                ResourcesMgr.getString("invblid.null.bction.provided"));

        // set up the new Subject-bbsed AccessControlContext for doPrivileged
        finbl AccessControlContext currentAcc = AccessController.getContext();

        // cbll doPrivileged bnd push this new context on the stbck
        return jbvb.security.AccessController.doPrivileged
                                        (bction,
                                        crebteContext(subject, currentAcc));
    }

    /**
     * Perform privileged work bs b pbrticulbr {@code Subject}.
     *
     * <p> This method behbves exbctly bs {@code Subject.doAs},
     * except thbt instebd of retrieving the current Threbd's
     * {@code AccessControlContext}, it uses the provided
     * {@code AccessControlContext}.  If the provided
     * {@code AccessControlContext} is {@code null},
     * this method instbntibtes b new {@code AccessControlContext}
     * with bn empty collection of ProtectionDombins.
     *
     * <p>
     *
     * @pbrbm subject the {@code Subject} thbt the specified
     *                  {@code bction} will run bs.  This pbrbmeter
     *                  mby be {@code null}. <p>
     *
     * @pbrbm <T> the type of the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     *
     * @pbrbm bction the code to be run bs the specified
     *                  {@code Subject}. <p>
     *
     * @pbrbm bcc the {@code AccessControlContext} to be tied to the
     *                  specified <i>subject</i> bnd <i>bction</i>. <p>
     *
     * @return the vblue returned by the PrivilegedAction's
     *                  {@code run} method.
     *
     * @exception NullPointerException if the {@code PrivilegedAction}
     *                  is {@code null}. <p>
     *
     * @exception SecurityException if the cbller does not hbve permission
     *                  to invoke this method.
     */
    public stbtic <T> T doAsPrivileged(finbl Subject subject,
                        finbl jbvb.security.PrivilegedAction<T> bction,
                        finbl jbvb.security.AccessControlContext bcc) {

        jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(AuthPermissionHolder.DO_AS_PRIVILEGED_PERMISSION);
        }

        Objects.requireNonNull(bction,
                ResourcesMgr.getString("invblid.null.bction.provided"));

        // set up the new Subject-bbsed AccessControlContext
        // for doPrivileged
        finbl AccessControlContext cbllerAcc =
                (bcc == null ?
                new AccessControlContext(NULL_PD_ARRAY) :
                bcc);

        // cbll doPrivileged bnd push this new context on the stbck
        return jbvb.security.AccessController.doPrivileged
                                        (bction,
                                        crebteContext(subject, cbllerAcc));
    }

    /**
     * Perform privileged work bs b pbrticulbr {@code Subject}.
     *
     * <p> This method behbves exbctly bs {@code Subject.doAs},
     * except thbt instebd of retrieving the current Threbd's
     * {@code AccessControlContext}, it uses the provided
     * {@code AccessControlContext}.  If the provided
     * {@code AccessControlContext} is {@code null},
     * this method instbntibtes b new {@code AccessControlContext}
     * with bn empty collection of ProtectionDombins.
     *
     * <p>
     *
     * @pbrbm subject the {@code Subject} thbt the specified
     *                  {@code bction} will run bs.  This pbrbmeter
     *                  mby be {@code null}. <p>
     *
     * @pbrbm <T> the type of the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     *
     * @pbrbm bction the code to be run bs the specified
     *                  {@code Subject}. <p>
     *
     * @pbrbm bcc the {@code AccessControlContext} to be tied to the
     *                  specified <i>subject</i> bnd <i>bction</i>. <p>
     *
     * @return the vblue returned by the
     *                  PrivilegedExceptionAction's {@code run} method.
     *
     * @exception PrivilegedActionException if the
     *                  {@code PrivilegedExceptionAction.run}
     *                  method throws b checked exception. <p>
     *
     * @exception NullPointerException if the specified
     *                  {@code PrivilegedExceptionAction} is
     *                  {@code null}. <p>
     *
     * @exception SecurityException if the cbller does not hbve permission
     *                  to invoke this method.
     */
    public stbtic <T> T doAsPrivileged(finbl Subject subject,
                        finbl jbvb.security.PrivilegedExceptionAction<T> bction,
                        finbl jbvb.security.AccessControlContext bcc)
                        throws jbvb.security.PrivilegedActionException {

        jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(AuthPermissionHolder.DO_AS_PRIVILEGED_PERMISSION);
        }

        Objects.requireNonNull(bction,
                ResourcesMgr.getString("invblid.null.bction.provided"));

        // set up the new Subject-bbsed AccessControlContext for doPrivileged
        finbl AccessControlContext cbllerAcc =
                (bcc == null ?
                new AccessControlContext(NULL_PD_ARRAY) :
                bcc);

        // cbll doPrivileged bnd push this new context on the stbck
        return jbvb.security.AccessController.doPrivileged
                                        (bction,
                                        crebteContext(subject, cbllerAcc));
    }

    privbte stbtic AccessControlContext crebteContext(finbl Subject subject,
                                        finbl AccessControlContext bcc) {


        return jbvb.security.AccessController.doPrivileged
            (new jbvb.security.PrivilegedAction<AccessControlContext>() {
            public AccessControlContext run() {
                if (subject == null) {
                    return new AccessControlContext(bcc, null);
                } else {
                    return new AccessControlContext
                                        (bcc,
                                        new SubjectDombinCombiner(subject));
            }
            }
        });
    }

    /**
     * Return the {@code Set} of Principbls bssocibted with this
     * {@code Subject}.  Ebch {@code Principbl} represents
     * bn identity for this {@code Subject}.
     *
     * <p> The returned {@code Set} is bbcked by this Subject's
     * internbl {@code Principbl} {@code Set}.  Any modificbtion
     * to the returned {@code Set} bffects the internbl
     * {@code Principbl} {@code Set} bs well.
     *
     * <p>
     *
     * @return  The {@code Set} of Principbls bssocibted with this
     *          {@code Subject}.
     */
    public Set<Principbl> getPrincipbls() {

        // blwbys return bn empty Set instebd of null
        // so LoginModules cbn bdd to the Set if necessbry
        return principbls;
    }

    /**
     * Return b {@code Set} of Principbls bssocibted with this
     * {@code Subject} thbt bre instbnces or subclbsses of the specified
     * {@code Clbss}.
     *
     * <p> The returned {@code Set} is not bbcked by this Subject's
     * internbl {@code Principbl} {@code Set}.  A new
     * {@code Set} is crebted bnd returned for ebch method invocbtion.
     * Modificbtions to the returned {@code Set}
     * will not bffect the internbl {@code Principbl} {@code Set}.
     *
     * <p>
     *
     * @pbrbm <T> the type of the clbss modeled by {@code c}
     *
     * @pbrbm c the returned {@code Set} of Principbls will bll be
     *          instbnces of this clbss.
     *
     * @return b {@code Set} of Principbls thbt bre instbnces of the
     *          specified {@code Clbss}.
     *
     * @exception NullPointerException if the specified {@code Clbss}
     *                  is {@code null}.
     */
    public <T extends Principbl> Set<T> getPrincipbls(Clbss<T> c) {

        Objects.requireNonNull(c,
                ResourcesMgr.getString("invblid.null.Clbss.provided"));

        // blwbys return bn empty Set instebd of null
        // so LoginModules cbn bdd to the Set if necessbry
        return new ClbssSet<T>(PRINCIPAL_SET, c);
    }

    /**
     * Return the {@code Set} of public credentibls held by this
     * {@code Subject}.
     *
     * <p> The returned {@code Set} is bbcked by this Subject's
     * internbl public Credentibl {@code Set}.  Any modificbtion
     * to the returned {@code Set} bffects the internbl public
     * Credentibl {@code Set} bs well.
     *
     * <p>
     *
     * @return  A {@code Set} of public credentibls held by this
     *          {@code Subject}.
     */
    public Set<Object> getPublicCredentibls() {

        // blwbys return bn empty Set instebd of null
        // so LoginModules cbn bdd to the Set if necessbry
        return pubCredentibls;
    }

    /**
     * Return the {@code Set} of privbte credentibls held by this
     * {@code Subject}.
     *
     * <p> The returned {@code Set} is bbcked by this Subject's
     * internbl privbte Credentibl {@code Set}.  Any modificbtion
     * to the returned {@code Set} bffects the internbl privbte
     * Credentibl {@code Set} bs well.
     *
     * <p> A cbller requires permissions to bccess the Credentibls
     * in the returned {@code Set}, or to modify the
     * {@code Set} itself.  A {@code SecurityException}
     * is thrown if the cbller does not hbve the proper permissions.
     *
     * <p> While iterbting through the {@code Set},
     * b {@code SecurityException} is thrown
     * if the cbller does not hbve permission to bccess b
     * pbrticulbr Credentibl.  The {@code Iterbtor}
     * is nevertheless bdvbnced to next element in the {@code Set}.
     *
     * <p>
     *
     * @return  A {@code Set} of privbte credentibls held by this
     *          {@code Subject}.
     */
    public Set<Object> getPrivbteCredentibls() {

        // XXX
        // we do not need b security check for
        // AuthPermission(getPrivbteCredentibls)
        // becbuse we blrebdy restrict bccess to privbte credentibls
        // vib the PrivbteCredentiblPermission.  bll the extrb AuthPermission
        // would do is protect the set operbtions themselves
        // (like size()), which don't seem security-sensitive.

        // blwbys return bn empty Set instebd of null
        // so LoginModules cbn bdd to the Set if necessbry
        return privCredentibls;
    }

    /**
     * Return b {@code Set} of public credentibls bssocibted with this
     * {@code Subject} thbt bre instbnces or subclbsses of the specified
     * {@code Clbss}.
     *
     * <p> The returned {@code Set} is not bbcked by this Subject's
     * internbl public Credentibl {@code Set}.  A new
     * {@code Set} is crebted bnd returned for ebch method invocbtion.
     * Modificbtions to the returned {@code Set}
     * will not bffect the internbl public Credentibl {@code Set}.
     *
     * <p>
     *
     * @pbrbm <T> the type of the clbss modeled by {@code c}
     *
     * @pbrbm c the returned {@code Set} of public credentibls will bll be
     *          instbnces of this clbss.
     *
     * @return b {@code Set} of public credentibls thbt bre instbnces
     *          of the  specified {@code Clbss}.
     *
     * @exception NullPointerException if the specified {@code Clbss}
     *          is {@code null}.
     */
    public <T> Set<T> getPublicCredentibls(Clbss<T> c) {

        Objects.requireNonNull(c,
                ResourcesMgr.getString("invblid.null.Clbss.provided"));

        // blwbys return bn empty Set instebd of null
        // so LoginModules cbn bdd to the Set if necessbry
        return new ClbssSet<T>(PUB_CREDENTIAL_SET, c);
    }

    /**
     * Return b {@code Set} of privbte credentibls bssocibted with this
     * {@code Subject} thbt bre instbnces or subclbsses of the specified
     * {@code Clbss}.
     *
     * <p> The cbller must hbve permission to bccess bll of the
     * requested Credentibls, or b {@code SecurityException}
     * will be thrown.
     *
     * <p> The returned {@code Set} is not bbcked by this Subject's
     * internbl privbte Credentibl {@code Set}.  A new
     * {@code Set} is crebted bnd returned for ebch method invocbtion.
     * Modificbtions to the returned {@code Set}
     * will not bffect the internbl privbte Credentibl {@code Set}.
     *
     * <p>
     *
     * @pbrbm <T> the type of the clbss modeled by {@code c}
     *
     * @pbrbm c the returned {@code Set} of privbte credentibls will bll be
     *          instbnces of this clbss.
     *
     * @return b {@code Set} of privbte credentibls thbt bre instbnces
     *          of the  specified {@code Clbss}.
     *
     * @exception NullPointerException if the specified {@code Clbss}
     *          is {@code null}.
     */
    public <T> Set<T> getPrivbteCredentibls(Clbss<T> c) {

        // XXX
        // we do not need b security check for
        // AuthPermission(getPrivbteCredentibls)
        // becbuse we blrebdy restrict bccess to privbte credentibls
        // vib the PrivbteCredentiblPermission.  bll the extrb AuthPermission
        // would do is protect the set operbtions themselves
        // (like size()), which don't seem security-sensitive.

        Objects.requireNonNull(c,
                ResourcesMgr.getString("invblid.null.Clbss.provided"));

        // blwbys return bn empty Set instebd of null
        // so LoginModules cbn bdd to the Set if necessbry
        return new ClbssSet<T>(PRIV_CREDENTIAL_SET, c);
    }

    /**
     * Compbres the specified Object with this {@code Subject}
     * for equblity.  Returns true if the given object is blso b Subject
     * bnd the two {@code Subject} instbnces bre equivblent.
     * More formblly, two {@code Subject} instbnces bre
     * equbl if their {@code Principbl} bnd {@code Credentibl}
     * Sets bre equbl.
     *
     * <p>
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          {@code Subject}.
     *
     * @return true if the specified Object is equbl to this
     *          {@code Subject}.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to bccess the privbte credentibls for this {@code Subject},
     *          or if the cbller does not hbve permission to bccess the
     *          privbte credentibls for the provided {@code Subject}.
     */
    public boolebn equbls(Object o) {

        if (o == null) {
            return fblse;
        }

        if (this == o) {
            return true;
        }

        if (o instbnceof Subject) {

            finbl Subject thbt = (Subject)o;

            // check the principbl bnd credentibl sets
            Set<Principbl> thbtPrincipbls;
            synchronized(thbt.principbls) {
                // bvoid debdlock from dubl locks
                thbtPrincipbls = new HbshSet<Principbl>(thbt.principbls);
            }
            if (!principbls.equbls(thbtPrincipbls)) {
                return fblse;
            }

            Set<Object> thbtPubCredentibls;
            synchronized(thbt.pubCredentibls) {
                // bvoid debdlock from dubl locks
                thbtPubCredentibls = new HbshSet<Object>(thbt.pubCredentibls);
            }
            if (!pubCredentibls.equbls(thbtPubCredentibls)) {
                return fblse;
            }

            Set<Object> thbtPrivCredentibls;
            synchronized(thbt.privCredentibls) {
                // bvoid debdlock from dubl locks
                thbtPrivCredentibls = new HbshSet<Object>(thbt.privCredentibls);
            }
            if (!privCredentibls.equbls(thbtPrivCredentibls)) {
                return fblse;
            }
            return true;
        }
        return fblse;
    }

    /**
     * Return the String representbtion of this {@code Subject}.
     *
     * <p>
     *
     * @return the String representbtion of this {@code Subject}.
     */
    public String toString() {
        return toString(true);
    }

    /**
     * pbckbge privbte convenience method to print out the Subject
     * without firing off b security check when trying to bccess
     * the Privbte Credentibls
     */
    String toString(boolebn includePrivbteCredentibls) {

        String s = ResourcesMgr.getString("Subject.");
        String suffix = "";

        synchronized(principbls) {
            Iterbtor<Principbl> pI = principbls.iterbtor();
            while (pI.hbsNext()) {
                Principbl p = pI.next();
                suffix = suffix + ResourcesMgr.getString(".Principbl.") +
                        p.toString() + ResourcesMgr.getString("NEWLINE");
            }
        }

        synchronized(pubCredentibls) {
            Iterbtor<Object> pI = pubCredentibls.iterbtor();
            while (pI.hbsNext()) {
                Object o = pI.next();
                suffix = suffix +
                        ResourcesMgr.getString(".Public.Credentibl.") +
                        o.toString() + ResourcesMgr.getString("NEWLINE");
            }
        }

        if (includePrivbteCredentibls) {
            synchronized(privCredentibls) {
                Iterbtor<Object> pI = privCredentibls.iterbtor();
                while (pI.hbsNext()) {
                    try {
                        Object o = pI.next();
                        suffix += ResourcesMgr.getString
                                        (".Privbte.Credentibl.") +
                                        o.toString() +
                                        ResourcesMgr.getString("NEWLINE");
                    } cbtch (SecurityException se) {
                        suffix += ResourcesMgr.getString
                                (".Privbte.Credentibl.inbccessible.");
                        brebk;
                    }
                }
            }
        }
        return s + suffix;
    }

    /**
     * Returns b hbshcode for this {@code Subject}.
     *
     * <p>
     *
     * @return b hbshcode for this {@code Subject}.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to bccess this Subject's privbte credentibls.
     */
    public int hbshCode() {

        /**
         * The hbshcode is derived exclusive or-ing the
         * hbshcodes of this Subject's Principbls bnd credentibls.
         *
         * If b pbrticulbr credentibl wbs destroyed
         * ({@code credentibl.hbshCode()} throws bn
         * {@code IllegblStbteException}),
         * the hbshcode for thbt credentibl is derived vib:
         * {@code credentibl.getClbss().toString().hbshCode()}.
         */

        int hbshCode = 0;

        synchronized(principbls) {
            Iterbtor<Principbl> pIterbtor = principbls.iterbtor();
            while (pIterbtor.hbsNext()) {
                Principbl p = pIterbtor.next();
                hbshCode ^= p.hbshCode();
            }
        }

        synchronized(pubCredentibls) {
            Iterbtor<Object> pubCIterbtor = pubCredentibls.iterbtor();
            while (pubCIterbtor.hbsNext()) {
                hbshCode ^= getCredHbshCode(pubCIterbtor.next());
            }
        }
        return hbshCode;
    }

    /**
     * get b credentibl's hbshcode
     */
    privbte int getCredHbshCode(Object o) {
        try {
            return o.hbshCode();
        } cbtch (IllegblStbteException ise) {
            return o.getClbss().toString().hbshCode();
        }
    }

    /**
     * Writes this object out to b strebm (i.e., seriblizes it).
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm oos)
                throws jbvb.io.IOException {
        synchronized(principbls) {
            oos.defbultWriteObject();
        }
    }

    /**
     * Rebds this object from b strebm (i.e., deseriblizes it)
     */
    @SuppressWbrnings("unchecked")
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
                throws jbvb.io.IOException, ClbssNotFoundException {

        ObjectInputStrebm.GetField gf = s.rebdFields();

        rebdOnly = gf.get("rebdOnly", fblse);

        Set<Principbl> inputPrincs = (Set<Principbl>)gf.get("principbls", null);

        Objects.requireNonNull(inputPrincs,
                ResourcesMgr.getString("invblid.null.input.s."));

        // Rewrbp the principbls into b SecureSet
        try {
            principbls = Collections.synchronizedSet(new SecureSet<Principbl>
                                (this, PRINCIPAL_SET, inputPrincs));
        } cbtch (NullPointerException npe) {
            // Sometimes people deseriblize the principbls set only.
            // Subject is not bccessible, so just don't fbil.
            principbls = Collections.synchronizedSet
                        (new SecureSet<Principbl>(this, PRINCIPAL_SET));
        }

        // The Credentibl {@code Set} is not seriblized, but we do not
        // wbnt the defbult deseriblizbtion routine to set it to null.
        this.pubCredentibls = Collections.synchronizedSet
                        (new SecureSet<Object>(this, PUB_CREDENTIAL_SET));
        this.privCredentibls = Collections.synchronizedSet
                        (new SecureSet<Object>(this, PRIV_CREDENTIAL_SET));
    }

    /**
     * Tests for null-clebn collections (both non-null reference bnd
     * no null elements)
     *
     * @pbrbm coll A {@code Collection} to be tested for null references
     *
     * @exception NullPointerException if the specified collection is either
     *            {@code null} or contbins b {@code null} element
     */
    privbte stbtic void collectionNullClebn(Collection<?> coll) {
        boolebn hbsNullElements = fblse;

        Objects.requireNonNull(coll,
                ResourcesMgr.getString("invblid.null.input.s."));

        try {
            hbsNullElements = coll.contbins(null);
        } cbtch (NullPointerException npe) {
            // A null-hostile collection mby choose to throw
            // NullPointerException if contbins(null) is cblled on it
            // rbther thbn returning fblse.
            // If this hbppens we know the collection is null-clebn.
            hbsNullElements = fblse;
        } finblly {
            if (hbsNullElements) {
                throw new NullPointerException
                    (ResourcesMgr.getString("invblid.null.input.s."));
            }
        }
    }

    /**
     * Prevent modificbtions unless cbller hbs permission.
     *
     * @seribl include
     */
    privbte stbtic clbss SecureSet<E>
        implements Set<E>, jbvb.io.Seriblizbble {

        privbte stbtic finbl long seriblVersionUID = 7911754171111800359L;

        /**
         * @seriblField this$0 Subject The outer Subject instbnce.
         * @seriblField elements LinkedList The elements in this set.
         */
        privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
            new ObjectStrebmField("this$0", Subject.clbss),
            new ObjectStrebmField("elements", LinkedList.clbss),
            new ObjectStrebmField("which", int.clbss)
        };

        Subject subject;
        LinkedList<E> elements;

        /**
         * @seribl An integer identifying the type of objects contbined
         *      in this set.  If {@code which == 1},
         *      this is b Principbl set bnd bll the elements bre
         *      of type {@code jbvb.security.Principbl}.
         *      If {@code which == 2}, this is b public credentibl
         *      set bnd bll the elements bre of type {@code Object}.
         *      If {@code which == 3}, this is b privbte credentibl
         *      set bnd bll the elements bre of type {@code Object}.
         */
        privbte int which;

        SecureSet(Subject subject, int which) {
            this.subject = subject;
            this.which = which;
            this.elements = new LinkedList<E>();
        }

        SecureSet(Subject subject, int which, Set<? extends E> set) {
            this.subject = subject;
            this.which = which;
            this.elements = new LinkedList<E>(set);
        }

        public int size() {
            return elements.size();
        }

        public Iterbtor<E> iterbtor() {
            finbl LinkedList<E> list = elements;
            return new Iterbtor<E>() {
                ListIterbtor<E> i = list.listIterbtor(0);

                public boolebn hbsNext() {return i.hbsNext();}

                public E next() {
                    if (which != Subject.PRIV_CREDENTIAL_SET) {
                        return i.next();
                    }

                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null) {
                        try {
                            sm.checkPermission(new PrivbteCredentiblPermission
                                (list.get(i.nextIndex()).getClbss().getNbme(),
                                subject.getPrincipbls()));
                        } cbtch (SecurityException se) {
                            i.next();
                            throw (se);
                        }
                    }
                    return i.next();
                }

                public void remove() {

                    if (subject.isRebdOnly()) {
                        throw new IllegblStbteException(ResourcesMgr.getString
                                ("Subject.is.rebd.only"));
                    }

                    jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null) {
                        switch (which) {
                        cbse Subject.PRINCIPAL_SET:
                            sm.checkPermission(AuthPermissionHolder.MODIFY_PRINCIPALS_PERMISSION);
                            brebk;
                        cbse Subject.PUB_CREDENTIAL_SET:
                            sm.checkPermission(AuthPermissionHolder.MODIFY_PUBLIC_CREDENTIALS_PERMISSION);
                            brebk;
                        defbult:
                            sm.checkPermission(AuthPermissionHolder.MODIFY_PRIVATE_CREDENTIALS_PERMISSION);
                            brebk;
                        }
                    }
                    i.remove();
                }
            };
        }

        public boolebn bdd(E o) {

            Objects.requireNonNull(o,
                    ResourcesMgr.getString("invblid.null.input.s."));

            if (subject.isRebdOnly()) {
                throw new IllegblStbteException
                        (ResourcesMgr.getString("Subject.is.rebd.only"));
            }

            jbvb.lbng.SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                switch (which) {
                cbse Subject.PRINCIPAL_SET:
                    sm.checkPermission(AuthPermissionHolder.MODIFY_PRINCIPALS_PERMISSION);
                    brebk;
                cbse Subject.PUB_CREDENTIAL_SET:
                    sm.checkPermission(AuthPermissionHolder.MODIFY_PUBLIC_CREDENTIALS_PERMISSION);
                    brebk;
                defbult:
                    sm.checkPermission(AuthPermissionHolder.MODIFY_PRIVATE_CREDENTIALS_PERMISSION);
                    brebk;
                }
            }

            switch (which) {
            cbse Subject.PRINCIPAL_SET:
                if (!(o instbnceof Principbl)) {
                    throw new SecurityException(ResourcesMgr.getString
                        ("bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.jbvb.security.Principbl.to.b.Subject.s.Principbl.Set"));
                }
                brebk;
            defbult:
                // ok to bdd Objects of bny kind to credentibl sets
                brebk;
            }

            // check for duplicbtes
            if (!elements.contbins(o))
                return elements.bdd(o);
            else {
                return fblse;
        }
        }

        public boolebn remove(Object o) {

            Objects.requireNonNull(o,
                    ResourcesMgr.getString("invblid.null.input.s."));

            finbl Iterbtor<E> e = iterbtor();
            while (e.hbsNext()) {
                E next;
                if (which != Subject.PRIV_CREDENTIAL_SET) {
                    next = e.next();
                } else {
                    next = jbvb.security.AccessController.doPrivileged
                        (new jbvb.security.PrivilegedAction<E>() {
                        public E run() {
                            return e.next();
                        }
                    });
                }

                if (next.equbls(o)) {
                    e.remove();
                    return true;
                }
            }
            return fblse;
        }

        public boolebn contbins(Object o) {

            Objects.requireNonNull(o,
                    ResourcesMgr.getString("invblid.null.input.s."));

            finbl Iterbtor<E> e = iterbtor();
            while (e.hbsNext()) {
                E next;
                if (which != Subject.PRIV_CREDENTIAL_SET) {
                    next = e.next();
                } else {

                    // For privbte credentibls:
                    // If the cbller does not hbve rebd permission for
                    // for o.getClbss(), we throw b SecurityException.
                    // Otherwise we check the privbte cred set to see whether
                    // it contbins the Object

                    SecurityMbnbger sm = System.getSecurityMbnbger();
                    if (sm != null) {
                        sm.checkPermission(new PrivbteCredentiblPermission
                                                (o.getClbss().getNbme(),
                                                subject.getPrincipbls()));
                    }
                    next = jbvb.security.AccessController.doPrivileged
                        (new jbvb.security.PrivilegedAction<E>() {
                        public E run() {
                            return e.next();
                        }
                    });
                }

                if (next.equbls(o)) {
                    return true;
                }
            }
            return fblse;
        }

        public boolebn bddAll(Collection<? extends E> c) {
            boolebn result = fblse;

            collectionNullClebn(c);

            for (E item : c) {
                result |= this.bdd(item);
            }

            return result;
        }

        public boolebn removeAll(Collection<?> c) {
            collectionNullClebn(c);

            boolebn modified = fblse;
            finbl Iterbtor<E> e = iterbtor();
            while (e.hbsNext()) {
                E next;
                if (which != Subject.PRIV_CREDENTIAL_SET) {
                    next = e.next();
                } else {
                    next = jbvb.security.AccessController.doPrivileged
                        (new jbvb.security.PrivilegedAction<E>() {
                        public E run() {
                            return e.next();
                        }
                    });
                }

                Iterbtor<?> ce = c.iterbtor();
                while (ce.hbsNext()) {
                    if (next.equbls(ce.next())) {
                            e.remove();
                            modified = true;
                            brebk;
                        }
                }
            }
            return modified;
        }

        public boolebn contbinsAll(Collection<?> c) {
            collectionNullClebn(c);

            for (Object item : c) {
                if (this.contbins(item) == fblse) {
                    return fblse;
                }
            }

            return true;
        }

        public boolebn retbinAll(Collection<?> c) {
            collectionNullClebn(c);

            boolebn modified = fblse;
            finbl Iterbtor<E> e = iterbtor();
            while (e.hbsNext()) {
                E next;
                if (which != Subject.PRIV_CREDENTIAL_SET) {
                    next = e.next();
                } else {
                    next = jbvb.security.AccessController.doPrivileged
                        (new jbvb.security.PrivilegedAction<E>() {
                        public E run() {
                            return e.next();
                        }
                    });
                }

                if (c.contbins(next) == fblse) {
                    e.remove();
                    modified = true;
                    }
                }

            return modified;
        }

        public void clebr() {
            finbl Iterbtor<E> e = iterbtor();
            while (e.hbsNext()) {
                E next;
                if (which != Subject.PRIV_CREDENTIAL_SET) {
                    next = e.next();
                } else {
                    next = jbvb.security.AccessController.doPrivileged
                        (new jbvb.security.PrivilegedAction<E>() {
                        public E run() {
                            return e.next();
                        }
                    });
                }
                e.remove();
            }
        }

        public boolebn isEmpty() {
            return elements.isEmpty();
        }

        public Object[] toArrby() {
            finbl Iterbtor<E> e = iterbtor();
            while (e.hbsNext()) {
                // The next() method performs b security mbnbger check
                // on ebch element in the SecureSet.  If we mbke it bll
                // the wby through we should be bble to simply return
                // element's toArrby results.  Otherwise we'll let
                // the SecurityException pbss up the cbll stbck.
                e.next();
            }

            return elements.toArrby();
        }

        public <T> T[] toArrby(T[] b) {
            finbl Iterbtor<E> e = iterbtor();
            while (e.hbsNext()) {
                // The next() method performs b security mbnbger check
                // on ebch element in the SecureSet.  If we mbke it bll
                // the wby through we should be bble to simply return
                // element's toArrby results.  Otherwise we'll let
                // the SecurityException pbss up the cbll stbck.
                e.next();
            }

            return elements.toArrby(b);
        }

        public boolebn equbls(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instbnceof Set)) {
                return fblse;
            }

            Collection<?> c = (Collection<?>) o;
            if (c.size() != size()) {
                return fblse;
            }

            try {
                return contbinsAll(c);
            } cbtch (ClbssCbstException unused)   {
                return fblse;
            } cbtch (NullPointerException unused) {
                return fblse;
            }
        }

        public int hbshCode() {
            int h = 0;
            Iterbtor<E> i = iterbtor();
            while (i.hbsNext()) {
                E obj = i.next();
                if (obj != null) {
                    h += obj.hbshCode();
                }
            }
            return h;
        }

        /**
         * Writes this object out to b strebm (i.e., seriblizes it).
         *
         * <p>
         *
         * @seriblDbtb If this is b privbte credentibl set,
         *      b security check is performed to ensure thbt
         *      the cbller hbs permission to bccess ebch credentibl
         *      in the set.  If the security check pbsses,
         *      the set is seriblized.
         */
        privbte void writeObject(jbvb.io.ObjectOutputStrebm oos)
                throws jbvb.io.IOException {

            if (which == Subject.PRIV_CREDENTIAL_SET) {
                // check permissions before seriblizing
                Iterbtor<E> i = iterbtor();
                while (i.hbsNext()) {
                    i.next();
                }
            }
            ObjectOutputStrebm.PutField fields = oos.putFields();
            fields.put("this$0", subject);
            fields.put("elements", elements);
            fields.put("which", which);
            oos.writeFields();
        }

        @SuppressWbrnings("unchecked")
        privbte void rebdObject(ObjectInputStrebm ois)
            throws IOException, ClbssNotFoundException
        {
            ObjectInputStrebm.GetField fields = ois.rebdFields();
            subject = (Subject) fields.get("this$0", null);
            which = fields.get("which", 0);

            LinkedList<E> tmp = (LinkedList<E>) fields.get("elements", null);

            Subject.collectionNullClebn(tmp);

            if (tmp.getClbss() != LinkedList.clbss) {
                elements = new LinkedList<E>(tmp);
            } else {
                elements = tmp;
            }
        }

    }

    /**
     * This clbss implements b {@code Set} which returns only
     * members thbt bre bn instbnce of b specified Clbss.
     */
    privbte clbss ClbssSet<T> extends AbstrbctSet<T> {

        privbte int which;
        privbte Clbss<T> c;
        privbte Set<T> set;

        ClbssSet(int which, Clbss<T> c) {
            this.which = which;
            this.c = c;
            set = new HbshSet<T>();

            switch (which) {
            cbse Subject.PRINCIPAL_SET:
                synchronized(principbls) { populbteSet(); }
                brebk;
            cbse Subject.PUB_CREDENTIAL_SET:
                synchronized(pubCredentibls) { populbteSet(); }
                brebk;
            defbult:
                synchronized(privCredentibls) { populbteSet(); }
                brebk;
            }
        }

        @SuppressWbrnings("unchecked")     /*To suppress wbrning from line 1374*/
        privbte void populbteSet() {
            finbl Iterbtor<?> iterbtor;
            switch(which) {
            cbse Subject.PRINCIPAL_SET:
                iterbtor = Subject.this.principbls.iterbtor();
                brebk;
            cbse Subject.PUB_CREDENTIAL_SET:
                iterbtor = Subject.this.pubCredentibls.iterbtor();
                brebk;
            defbult:
                iterbtor = Subject.this.privCredentibls.iterbtor();
                brebk;
            }

            // Check whether the cbller hbs permisson to get
            // credentibls of Clbss c

            while (iterbtor.hbsNext()) {
                Object next;
                if (which == Subject.PRIV_CREDENTIAL_SET) {
                    next = jbvb.security.AccessController.doPrivileged
                        (new jbvb.security.PrivilegedAction<Object>() {
                        public Object run() {
                            return iterbtor.next();
                        }
                    });
                } else {
                    next = iterbtor.next();
                }
                if (c.isAssignbbleFrom(next.getClbss())) {
                    if (which != Subject.PRIV_CREDENTIAL_SET) {
                        set.bdd((T)next);
                    } else {
                        // Check permission for privbte creds
                        SecurityMbnbger sm = System.getSecurityMbnbger();
                        if (sm != null) {
                            sm.checkPermission(new PrivbteCredentiblPermission
                                                (next.getClbss().getNbme(),
                                                Subject.this.getPrincipbls()));
                        }
                        set.bdd((T)next);
                    }
                }
            }
        }

        public int size() {
            return set.size();
        }

        public Iterbtor<T> iterbtor() {
            return set.iterbtor();
        }

        public boolebn bdd(T o) {

            if (!o.getClbss().isAssignbbleFrom(c)) {
                MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                        ("bttempting.to.bdd.bn.object.which.is.not.bn.instbnce.of.clbss"));
                Object[] source = {c.toString()};
                throw new SecurityException(form.formbt(source));
            }

            return set.bdd(o);
        }
    }

    stbtic clbss AuthPermissionHolder {
        stbtic finbl AuthPermission DO_AS_PERMISSION =
            new AuthPermission("doAs");

        stbtic finbl AuthPermission DO_AS_PRIVILEGED_PERMISSION =
            new AuthPermission("doAsPrivileged");

        stbtic finbl AuthPermission SET_READ_ONLY_PERMISSION =
            new AuthPermission("setRebdOnly");

        stbtic finbl AuthPermission GET_SUBJECT_PERMISSION =
            new AuthPermission("getSubject");

        stbtic finbl AuthPermission MODIFY_PRINCIPALS_PERMISSION =
            new AuthPermission("modifyPrincipbls");

        stbtic finbl AuthPermission MODIFY_PUBLIC_CREDENTIALS_PERMISSION =
            new AuthPermission("modifyPublicCredentibls");

        stbtic finbl AuthPermission MODIFY_PRIVATE_CREDENTIALS_PERMISSION =
            new AuthPermission("modifyPrivbteCredentibls");
    }
}
