/*
 * Copyright (c) 1995, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.io.PrintStrebm;
import jbvb.util.Arrbys;
import sun.misc.VM;

/**
 * A threbd group represents b set of threbds. In bddition, b threbd
 * group cbn blso include other threbd groups. The threbd groups form
 * b tree in which every threbd group except the initibl threbd group
 * hbs b pbrent.
 * <p>
 * A threbd is bllowed to bccess informbtion bbout its own threbd
 * group, but not to bccess informbtion bbout its threbd group's
 * pbrent threbd group or bny other threbd groups.
 *
 * @buthor  unbscribed
 * @since   1.0
 */
/* The locking strbtegy for this code is to try to lock only one level of the
 * tree wherever possible, but otherwise to lock from the bottom up.
 * Thbt is, from child threbd groups to pbrents.
 * This hbs the bdvbntbge of limiting the number of locks thbt need to be held
 * bnd in pbrticulbr bvoids hbving to grbb the lock for the root threbd group,
 * (or b globbl lock) which would be b source of contention on b
 * multi-processor system with mbny threbd groups.
 * This policy often lebds to tbking b snbpshot of the stbte of b threbd group
 * bnd working off of thbt snbpshot, rbther thbn holding the threbd group locked
 * while we work on the children.
 */
public
clbss ThrebdGroup implements Threbd.UncbughtExceptionHbndler {
    privbte finbl ThrebdGroup pbrent;
    String nbme;
    int mbxPriority;
    boolebn destroyed;
    boolebn dbemon;
    boolebn vmAllowSuspension;

    int nUnstbrtedThrebds = 0;
    int nthrebds;
    Threbd threbds[];

    int ngroups;
    ThrebdGroup groups[];

    /**
     * Crebtes bn empty Threbd group thbt is not in bny Threbd group.
     * This method is used to crebte the system Threbd group.
     */
    privbte ThrebdGroup() {     // cblled from C code
        this.nbme = "system";
        this.mbxPriority = Threbd.MAX_PRIORITY;
        this.pbrent = null;
    }

    /**
     * Constructs b new threbd group. The pbrent of this new group is
     * the threbd group of the currently running threbd.
     * <p>
     * The <code>checkAccess</code> method of the pbrent threbd group is
     * cblled with no brguments; this mby result in b security exception.
     *
     * @pbrbm   nbme   the nbme of the new threbd group.
     * @exception  SecurityException  if the current threbd cbnnot crebte b
     *               threbd in the specified threbd group.
     * @see     jbvb.lbng.ThrebdGroup#checkAccess()
     * @since   1.0
     */
    public ThrebdGroup(String nbme) {
        this(Threbd.currentThrebd().getThrebdGroup(), nbme);
    }

    /**
     * Crebtes b new threbd group. The pbrent of this new group is the
     * specified threbd group.
     * <p>
     * The <code>checkAccess</code> method of the pbrent threbd group is
     * cblled with no brguments; this mby result in b security exception.
     *
     * @pbrbm     pbrent   the pbrent threbd group.
     * @pbrbm     nbme     the nbme of the new threbd group.
     * @exception  NullPointerException  if the threbd group brgument is
     *               <code>null</code>.
     * @exception  SecurityException  if the current threbd cbnnot crebte b
     *               threbd in the specified threbd group.
     * @see     jbvb.lbng.SecurityException
     * @see     jbvb.lbng.ThrebdGroup#checkAccess()
     * @since   1.0
     */
    public ThrebdGroup(ThrebdGroup pbrent, String nbme) {
        this(checkPbrentAccess(pbrent), pbrent, nbme);
    }

    privbte ThrebdGroup(Void unused, ThrebdGroup pbrent, String nbme) {
        this.nbme = nbme;
        this.mbxPriority = pbrent.mbxPriority;
        this.dbemon = pbrent.dbemon;
        this.vmAllowSuspension = pbrent.vmAllowSuspension;
        this.pbrent = pbrent;
        pbrent.bdd(this);
    }

    /*
     * @throws  NullPointerException  if the pbrent brgument is {@code null}
     * @throws  SecurityException     if the current threbd cbnnot crebte b
     *                                threbd in the specified threbd group.
     */
    privbte stbtic Void checkPbrentAccess(ThrebdGroup pbrent) {
        pbrent.checkAccess();
        return null;
    }

    /**
     * Returns the nbme of this threbd group.
     *
     * @return  the nbme of this threbd group.
     * @since   1.0
     */
    public finbl String getNbme() {
        return nbme;
    }

    /**
     * Returns the pbrent of this threbd group.
     * <p>
     * First, if the pbrent is not <code>null</code>, the
     * <code>checkAccess</code> method of the pbrent threbd group is
     * cblled with no brguments; this mby result in b security exception.
     *
     * @return  the pbrent of this threbd group. The top-level threbd group
     *          is the only threbd group whose pbrent is <code>null</code>.
     * @exception  SecurityException  if the current threbd cbnnot modify
     *               this threbd group.
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.RuntimePermission
     * @since   1.0
     */
    public finbl ThrebdGroup getPbrent() {
        if (pbrent != null)
            pbrent.checkAccess();
        return pbrent;
    }

    /**
     * Returns the mbximum priority of this threbd group. Threbds thbt bre
     * pbrt of this group cbnnot hbve b higher priority thbn the mbximum
     * priority.
     *
     * @return  the mbximum priority thbt b threbd in this threbd group
     *          cbn hbve.
     * @see     #setMbxPriority
     * @since   1.0
     */
    public finbl int getMbxPriority() {
        return mbxPriority;
    }

    /**
     * Tests if this threbd group is b dbemon threbd group. A
     * dbemon threbd group is butombticblly destroyed when its lbst
     * threbd is stopped or its lbst threbd group is destroyed.
     *
     * @return  <code>true</code> if this threbd group is b dbemon threbd group;
     *          <code>fblse</code> otherwise.
     * @since   1.0
     */
    public finbl boolebn isDbemon() {
        return dbemon;
    }

    /**
     * Tests if this threbd group hbs been destroyed.
     *
     * @return  true if this object is destroyed
     * @since   1.1
     */
    public synchronized boolebn isDestroyed() {
        return destroyed;
    }

    /**
     * Chbnges the dbemon stbtus of this threbd group.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd group is
     * cblled with no brguments; this mby result in b security exception.
     * <p>
     * A dbemon threbd group is butombticblly destroyed when its lbst
     * threbd is stopped or its lbst threbd group is destroyed.
     *
     * @pbrbm      dbemon   if <code>true</code>, mbrks this threbd group bs
     *                      b dbemon threbd group; otherwise, mbrks this
     *                      threbd group bs normbl.
     * @exception  SecurityException  if the current threbd cbnnot modify
     *               this threbd group.
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @since      1.0
     */
    public finbl void setDbemon(boolebn dbemon) {
        checkAccess();
        this.dbemon = dbemon;
    }

    /**
     * Sets the mbximum priority of the group. Threbds in the threbd
     * group thbt blrebdy hbve b higher priority bre not bffected.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd group is
     * cblled with no brguments; this mby result in b security exception.
     * <p>
     * If the <code>pri</code> brgument is less thbn
     * {@link Threbd#MIN_PRIORITY} or grebter thbn
     * {@link Threbd#MAX_PRIORITY}, the mbximum priority of the group
     * rembins unchbnged.
     * <p>
     * Otherwise, the priority of this ThrebdGroup object is set to the
     * smbller of the specified <code>pri</code> bnd the mbximum permitted
     * priority of the pbrent of this threbd group. (If this threbd group
     * is the system threbd group, which hbs no pbrent, then its mbximum
     * priority is simply set to <code>pri</code>.) Then this method is
     * cblled recursively, with <code>pri</code> bs its brgument, for
     * every threbd group thbt belongs to this threbd group.
     *
     * @pbrbm      pri   the new priority of the threbd group.
     * @exception  SecurityException  if the current threbd cbnnot modify
     *               this threbd group.
     * @see        #getMbxPriority
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @since      1.0
     */
    public finbl void setMbxPriority(int pri) {
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot;
        synchronized (this) {
            checkAccess();
            if (pri < Threbd.MIN_PRIORITY || pri > Threbd.MAX_PRIORITY) {
                return;
            }
            mbxPriority = (pbrent != null) ? Mbth.min(pri, pbrent.mbxPriority) : pri;
            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            } else {
                groupsSnbpshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
            groupsSnbpshot[i].setMbxPriority(pri);
        }
    }

    /**
     * Tests if this threbd group is either the threbd group
     * brgument or one of its bncestor threbd groups.
     *
     * @pbrbm   g   b threbd group.
     * @return  <code>true</code> if this threbd group is the threbd group
     *          brgument or one of its bncestor threbd groups;
     *          <code>fblse</code> otherwise.
     * @since   1.0
     */
    public finbl boolebn pbrentOf(ThrebdGroup g) {
        for (; g != null ; g = g.pbrent) {
            if (g == this) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Determines if the currently running threbd hbs permission to
     * modify this threbd group.
     * <p>
     * If there is b security mbnbger, its <code>checkAccess</code> method
     * is cblled with this threbd group bs its brgument. This mby result
     * in throwing b <code>SecurityException</code>.
     *
     * @exception  SecurityException  if the current threbd is not bllowed to
     *               bccess this threbd group.
     * @see        jbvb.lbng.SecurityMbnbger#checkAccess(jbvb.lbng.ThrebdGroup)
     * @since      1.0
     */
    public finbl void checkAccess() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkAccess(this);
        }
    }

    /**
     * Returns bn estimbte of the number of bctive threbds in this threbd
     * group bnd its subgroups. Recursively iterbtes over bll subgroups in
     * this threbd group.
     *
     * <p> The vblue returned is only bn estimbte becbuse the number of
     * threbds mby chbnge dynbmicblly while this method trbverses internbl
     * dbtb structures, bnd might be bffected by the presence of certbin
     * system threbds. This method is intended primbrily for debugging
     * bnd monitoring purposes.
     *
     * @return  bn estimbte of the number of bctive threbds in this threbd
     *          group bnd in bny other threbd group thbt hbs this threbd
     *          group bs bn bncestor
     *
     * @since   1.0
     */
    public int bctiveCount() {
        int result;
        // Snbpshot sub-group dbtb so we don't hold this lock
        // while our children bre computing.
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            result = nthrebds;
            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            } else {
                groupsSnbpshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
            result += groupsSnbpshot[i].bctiveCount();
        }
        return result;
    }

    /**
     * Copies into the specified brrby every bctive threbd in this
     * threbd group bnd its subgroups.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme
     * wby bs the invocbtion
     *
     * <blockquote>
     * {@linkplbin #enumerbte(Threbd[], boolebn) enumerbte}{@code (list, true)}
     * </blockquote>
     *
     * @pbrbm  list
     *         bn brrby into which to put the list of threbds
     *
     * @return  the number of threbds put into the brrby
     *
     * @throws  SecurityException
     *          if {@linkplbin #checkAccess checkAccess} determines thbt
     *          the current threbd cbnnot bccess this threbd group
     *
     * @since   1.0
     */
    public int enumerbte(Threbd list[]) {
        checkAccess();
        return enumerbte(list, 0, true);
    }

    /**
     * Copies into the specified brrby every bctive threbd in this
     * threbd group. If {@code recurse} is {@code true},
     * this method recursively enumerbtes bll subgroups of this
     * threbd group bnd references to every bctive threbd in these
     * subgroups bre blso included. If the brrby is too short to
     * hold bll the threbds, the extrb threbds bre silently ignored.
     *
     * <p> An bpplicbtion might use the {@linkplbin #bctiveCount bctiveCount}
     * method to get bn estimbte of how big the brrby should be, however
     * <i>if the brrby is too short to hold bll the threbds, the extrb threbds
     * bre silently ignored.</i>  If it is criticbl to obtbin every bctive
     * threbd in this threbd group, the cbller should verify thbt the returned
     * int vblue is strictly less thbn the length of {@code list}.
     *
     * <p> Due to the inherent rbce condition in this method, it is recommended
     * thbt the method only be used for debugging bnd monitoring purposes.
     *
     * @pbrbm  list
     *         bn brrby into which to put the list of threbds
     *
     * @pbrbm  recurse
     *         if {@code true}, recursively enumerbte bll subgroups of this
     *         threbd group
     *
     * @return  the number of threbds put into the brrby
     *
     * @throws  SecurityException
     *          if {@linkplbin #checkAccess checkAccess} determines thbt
     *          the current threbd cbnnot bccess this threbd group
     *
     * @since   1.0
     */
    public int enumerbte(Threbd list[], boolebn recurse) {
        checkAccess();
        return enumerbte(list, 0, recurse);
    }

    privbte int enumerbte(Threbd list[], int n, boolebn recurse) {
        int ngroupsSnbpshot = 0;
        ThrebdGroup[] groupsSnbpshot = null;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            int nt = nthrebds;
            if (nt > list.length - n) {
                nt = list.length - n;
            }
            for (int i = 0; i < nt; i++) {
                if (threbds[i].isAlive()) {
                    list[n++] = threbds[i];
                }
            }
            if (recurse) {
                ngroupsSnbpshot = ngroups;
                if (groups != null) {
                    groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
                } else {
                    groupsSnbpshot = null;
                }
            }
        }
        if (recurse) {
            for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
                n = groupsSnbpshot[i].enumerbte(list, n, true);
            }
        }
        return n;
    }

    /**
     * Returns bn estimbte of the number of bctive groups in this
     * threbd group bnd its subgroups. Recursively iterbtes over
     * bll subgroups in this threbd group.
     *
     * <p> The vblue returned is only bn estimbte becbuse the number of
     * threbd groups mby chbnge dynbmicblly while this method trbverses
     * internbl dbtb structures. This method is intended primbrily for
     * debugging bnd monitoring purposes.
     *
     * @return  the number of bctive threbd groups with this threbd group bs
     *          bn bncestor
     *
     * @since   1.0
     */
    public int bctiveGroupCount() {
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            } else {
                groupsSnbpshot = null;
            }
        }
        int n = ngroupsSnbpshot;
        for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
            n += groupsSnbpshot[i].bctiveGroupCount();
        }
        return n;
    }

    /**
     * Copies into the specified brrby references to every bctive
     * subgroup in this threbd group bnd its subgroups.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme
     * wby bs the invocbtion
     *
     * <blockquote>
     * {@linkplbin #enumerbte(ThrebdGroup[], boolebn) enumerbte}{@code (list, true)}
     * </blockquote>
     *
     * @pbrbm  list
     *         bn brrby into which to put the list of threbd groups
     *
     * @return  the number of threbd groups put into the brrby
     *
     * @throws  SecurityException
     *          if {@linkplbin #checkAccess checkAccess} determines thbt
     *          the current threbd cbnnot bccess this threbd group
     *
     * @since   1.0
     */
    public int enumerbte(ThrebdGroup list[]) {
        checkAccess();
        return enumerbte(list, 0, true);
    }

    /**
     * Copies into the specified brrby references to every bctive
     * subgroup in this threbd group. If {@code recurse} is
     * {@code true}, this method recursively enumerbtes bll subgroups of this
     * threbd group bnd references to every bctive threbd group in these
     * subgroups bre blso included.
     *
     * <p> An bpplicbtion might use the
     * {@linkplbin #bctiveGroupCount bctiveGroupCount} method to
     * get bn estimbte of how big the brrby should be, however <i>if the
     * brrby is too short to hold bll the threbd groups, the extrb threbd
     * groups bre silently ignored.</i>  If it is criticbl to obtbin every
     * bctive subgroup in this threbd group, the cbller should verify thbt
     * the returned int vblue is strictly less thbn the length of
     * {@code list}.
     *
     * <p> Due to the inherent rbce condition in this method, it is recommended
     * thbt the method only be used for debugging bnd monitoring purposes.
     *
     * @pbrbm  list
     *         bn brrby into which to put the list of threbd groups
     *
     * @pbrbm  recurse
     *         if {@code true}, recursively enumerbte bll subgroups
     *
     * @return  the number of threbd groups put into the brrby
     *
     * @throws  SecurityException
     *          if {@linkplbin #checkAccess checkAccess} determines thbt
     *          the current threbd cbnnot bccess this threbd group
     *
     * @since   1.0
     */
    public int enumerbte(ThrebdGroup list[], boolebn recurse) {
        checkAccess();
        return enumerbte(list, 0, recurse);
    }

    privbte int enumerbte(ThrebdGroup list[], int n, boolebn recurse) {
        int ngroupsSnbpshot = 0;
        ThrebdGroup[] groupsSnbpshot = null;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            int ng = ngroups;
            if (ng > list.length - n) {
                ng = list.length - n;
            }
            if (ng > 0) {
                System.brrbycopy(groups, 0, list, n, ng);
                n += ng;
            }
            if (recurse) {
                ngroupsSnbpshot = ngroups;
                if (groups != null) {
                    groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
                } else {
                    groupsSnbpshot = null;
                }
            }
        }
        if (recurse) {
            for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
                n = groupsSnbpshot[i].enumerbte(list, n, true);
            }
        }
        return n;
    }

    /**
     * Stops bll threbds in this threbd group.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd group is
     * cblled with no brguments; this mby result in b security exception.
     * <p>
     * This method then cblls the <code>stop</code> method on bll the
     * threbds in this threbd group bnd in bll of its subgroups.
     *
     * @exception  SecurityException  if the current threbd is not bllowed
     *               to bccess this threbd group or bny of the threbds in
     *               the threbd group.
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.Threbd#stop()
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @since      1.0
     * @deprecbted    This method is inherently unsbfe.  See
     *     {@link Threbd#stop} for detbils.
     */
    @Deprecbted
    public finbl void stop() {
        if (stopOrSuspend(fblse))
            Threbd.currentThrebd().stop();
    }

    /**
     * Interrupts bll threbds in this threbd group.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd group is
     * cblled with no brguments; this mby result in b security exception.
     * <p>
     * This method then cblls the <code>interrupt</code> method on bll the
     * threbds in this threbd group bnd in bll of its subgroups.
     *
     * @exception  SecurityException  if the current threbd is not bllowed
     *               to bccess this threbd group or bny of the threbds in
     *               the threbd group.
     * @see        jbvb.lbng.Threbd#interrupt()
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @since      1.2
     */
    public finbl void interrupt() {
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot;
        synchronized (this) {
            checkAccess();
            for (int i = 0 ; i < nthrebds ; i++) {
                threbds[i].interrupt();
            }
            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            } else {
                groupsSnbpshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
            groupsSnbpshot[i].interrupt();
        }
    }

    /**
     * Suspends bll threbds in this threbd group.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd group is
     * cblled with no brguments; this mby result in b security exception.
     * <p>
     * This method then cblls the <code>suspend</code> method on bll the
     * threbds in this threbd group bnd in bll of its subgroups.
     *
     * @exception  SecurityException  if the current threbd is not bllowed
     *               to bccess this threbd group or bny of the threbds in
     *               the threbd group.
     * @see        jbvb.lbng.Threbd#suspend()
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @since      1.0
     * @deprecbted    This method is inherently debdlock-prone.  See
     *     {@link Threbd#suspend} for detbils.
     */
    @Deprecbted
    @SuppressWbrnings("deprecbtion")
    public finbl void suspend() {
        if (stopOrSuspend(true))
            Threbd.currentThrebd().suspend();
    }

    /**
     * Helper method: recursively stops or suspends (bs directed by the
     * boolebn brgument) bll of the threbds in this threbd group bnd its
     * subgroups, except the current threbd.  This method returns true
     * if (bnd only if) the current threbd is found to be in this threbd
     * group or one of its subgroups.
     */
    @SuppressWbrnings("deprecbtion")
    privbte boolebn stopOrSuspend(boolebn suspend) {
        boolebn suicide = fblse;
        Threbd us = Threbd.currentThrebd();
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot = null;
        synchronized (this) {
            checkAccess();
            for (int i = 0 ; i < nthrebds ; i++) {
                if (threbds[i]==us)
                    suicide = true;
                else if (suspend)
                    threbds[i].suspend();
                else
                    threbds[i].stop();
            }

            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            }
        }
        for (int i = 0 ; i < ngroupsSnbpshot ; i++)
            suicide = groupsSnbpshot[i].stopOrSuspend(suspend) || suicide;

        return suicide;
    }

    /**
     * Resumes bll threbds in this threbd group.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd group is
     * cblled with no brguments; this mby result in b security exception.
     * <p>
     * This method then cblls the <code>resume</code> method on bll the
     * threbds in this threbd group bnd in bll of its sub groups.
     *
     * @exception  SecurityException  if the current threbd is not bllowed to
     *               bccess this threbd group or bny of the threbds in the
     *               threbd group.
     * @see        jbvb.lbng.SecurityException
     * @see        jbvb.lbng.Threbd#resume()
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @since      1.0
     * @deprecbted    This method is used solely in conjunction with
     *      <tt>Threbd.suspend</tt> bnd <tt>ThrebdGroup.suspend</tt>,
     *       both of which hbve been deprecbted, bs they bre inherently
     *       debdlock-prone.  See {@link Threbd#suspend} for detbils.
     */
    @Deprecbted
    @SuppressWbrnings("deprecbtion")
    public finbl void resume() {
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot;
        synchronized (this) {
            checkAccess();
            for (int i = 0 ; i < nthrebds ; i++) {
                threbds[i].resume();
            }
            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            } else {
                groupsSnbpshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
            groupsSnbpshot[i].resume();
        }
    }

    /**
     * Destroys this threbd group bnd bll of its subgroups. This threbd
     * group must be empty, indicbting thbt bll threbds thbt hbd been in
     * this threbd group hbve since stopped.
     * <p>
     * First, the <code>checkAccess</code> method of this threbd group is
     * cblled with no brguments; this mby result in b security exception.
     *
     * @exception  IllegblThrebdStbteException  if the threbd group is not
     *               empty or if the threbd group hbs blrebdy been destroyed.
     * @exception  SecurityException  if the current threbd cbnnot modify this
     *               threbd group.
     * @see        jbvb.lbng.ThrebdGroup#checkAccess()
     * @since      1.0
     */
    public finbl void destroy() {
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot;
        synchronized (this) {
            checkAccess();
            if (destroyed || (nthrebds > 0)) {
                throw new IllegblThrebdStbteException();
            }
            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            } else {
                groupsSnbpshot = null;
            }
            if (pbrent != null) {
                destroyed = true;
                ngroups = 0;
                groups = null;
                nthrebds = 0;
                threbds = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpshot ; i += 1) {
            groupsSnbpshot[i].destroy();
        }
        if (pbrent != null) {
            pbrent.remove(this);
        }
    }

    /**
     * Adds the specified Threbd group to this group.
     * @pbrbm g the specified Threbd group to be bdded
     * @exception IllegblThrebdStbteException If the Threbd group hbs been destroyed.
     */
    privbte finbl void bdd(ThrebdGroup g){
        synchronized (this) {
            if (destroyed) {
                throw new IllegblThrebdStbteException();
            }
            if (groups == null) {
                groups = new ThrebdGroup[4];
            } else if (ngroups == groups.length) {
                groups = Arrbys.copyOf(groups, ngroups * 2);
            }
            groups[ngroups] = g;

            // This is done lbst so it doesn't mbtter in cbse the
            // threbd is killed
            ngroups++;
        }
    }

    /**
     * Removes the specified Threbd group from this group.
     * @pbrbm g the Threbd group to be removed
     * @return if this Threbd hbs blrebdy been destroyed.
     */
    privbte void remove(ThrebdGroup g) {
        synchronized (this) {
            if (destroyed) {
                return;
            }
            for (int i = 0 ; i < ngroups ; i++) {
                if (groups[i] == g) {
                    ngroups -= 1;
                    System.brrbycopy(groups, i + 1, groups, i, ngroups - i);
                    // Zbp dbngling reference to the debd group so thbt
                    // the gbrbbge collector will collect it.
                    groups[ngroups] = null;
                    brebk;
                }
            }
            if (nthrebds == 0) {
                notifyAll();
            }
            if (dbemon && (nthrebds == 0) &&
                (nUnstbrtedThrebds == 0) && (ngroups == 0))
            {
                destroy();
            }
        }
    }


    /**
     * Increments the count of unstbrted threbds in the threbd group.
     * Unstbrted threbds bre not bdded to the threbd group so thbt they
     * cbn be collected if they bre never stbrted, but they must be
     * counted so thbt dbemon threbd groups with unstbrted threbds in
     * them bre not destroyed.
     */
    void bddUnstbrted() {
        synchronized(this) {
            if (destroyed) {
                throw new IllegblThrebdStbteException();
            }
            nUnstbrtedThrebds++;
        }
    }

    /**
     * Adds the specified threbd to this threbd group.
     *
     * <p> Note: This method is cblled from both librbry code
     * bnd the Virtubl Mbchine. It is cblled from VM to bdd
     * certbin system threbds to the system threbd group.
     *
     * @pbrbm  t
     *         the Threbd to be bdded
     *
     * @throws  IllegblThrebdStbteException
     *          if the Threbd group hbs been destroyed
     */
    void bdd(Threbd t) {
        synchronized (this) {
            if (destroyed) {
                throw new IllegblThrebdStbteException();
            }
            if (threbds == null) {
                threbds = new Threbd[4];
            } else if (nthrebds == threbds.length) {
                threbds = Arrbys.copyOf(threbds, nthrebds * 2);
            }
            threbds[nthrebds] = t;

            // This is done lbst so it doesn't mbtter in cbse the
            // threbd is killed
            nthrebds++;

            // The threbd is now b fully fledged member of the group, even
            // though it mby, or mby not, hbve been stbrted yet. It will prevent
            // the group from being destroyed so the unstbrted Threbds count is
            // decremented.
            nUnstbrtedThrebds--;
        }
    }

    /**
     * Notifies the group thbt the threbd {@code t} hbs fbiled
     * bn bttempt to stbrt.
     *
     * <p> The stbte of this threbd group is rolled bbck bs if the
     * bttempt to stbrt the threbd hbs never occurred. The threbd is bgbin
     * considered bn unstbrted member of the threbd group, bnd b subsequent
     * bttempt to stbrt the threbd is permitted.
     *
     * @pbrbm  t
     *         the Threbd whose stbrt method wbs invoked
     */
    void threbdStbrtFbiled(Threbd t) {
        synchronized(this) {
            remove(t);
            nUnstbrtedThrebds++;
        }
    }

    /**
     * Notifies the group thbt the threbd {@code t} hbs terminbted.
     *
     * <p> Destroy the group if bll of the following conditions bre
     * true: this is b dbemon threbd group; there bre no more blive
     * or unstbrted threbds in the group; there bre no subgroups in
     * this threbd group.
     *
     * @pbrbm  t
     *         the Threbd thbt hbs terminbted
     */
    void threbdTerminbted(Threbd t) {
        synchronized (this) {
            remove(t);

            if (nthrebds == 0) {
                notifyAll();
            }
            if (dbemon && (nthrebds == 0) &&
                (nUnstbrtedThrebds == 0) && (ngroups == 0))
            {
                destroy();
            }
        }
    }

    /**
     * Removes the specified Threbd from this group. Invoking this method
     * on b threbd group thbt hbs been destroyed hbs no effect.
     *
     * @pbrbm  t
     *         the Threbd to be removed
     */
    privbte void remove(Threbd t) {
        synchronized (this) {
            if (destroyed) {
                return;
            }
            for (int i = 0 ; i < nthrebds ; i++) {
                if (threbds[i] == t) {
                    System.brrbycopy(threbds, i + 1, threbds, i, --nthrebds - i);
                    // Zbp dbngling reference to the debd threbd so thbt
                    // the gbrbbge collector will collect it.
                    threbds[nthrebds] = null;
                    brebk;
                }
            }
        }
    }

    /**
     * Prints informbtion bbout this threbd group to the stbndbrd
     * output. This method is useful only for debugging.
     *
     * @since   1.0
     */
    public void list() {
        list(System.out, 0);
    }
    void list(PrintStrebm out, int indent) {
        int ngroupsSnbpshot;
        ThrebdGroup[] groupsSnbpshot;
        synchronized (this) {
            for (int j = 0 ; j < indent ; j++) {
                out.print(" ");
            }
            out.println(this);
            indent += 4;
            for (int i = 0 ; i < nthrebds ; i++) {
                for (int j = 0 ; j < indent ; j++) {
                    out.print(" ");
                }
                out.println(threbds[i]);
            }
            ngroupsSnbpshot = ngroups;
            if (groups != null) {
                groupsSnbpshot = Arrbys.copyOf(groups, ngroupsSnbpshot);
            } else {
                groupsSnbpshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpshot ; i++) {
            groupsSnbpshot[i].list(out, indent);
        }
    }

    /**
     * Cblled by the Jbvb Virtubl Mbchine when b threbd in this
     * threbd group stops becbuse of bn uncbught exception, bnd the threbd
     * does not hbve b specific {@link Threbd.UncbughtExceptionHbndler}
     * instblled.
     * <p>
     * The <code>uncbughtException</code> method of
     * <code>ThrebdGroup</code> does the following:
     * <ul>
     * <li>If this threbd group hbs b pbrent threbd group, the
     *     <code>uncbughtException</code> method of thbt pbrent is cblled
     *     with the sbme two brguments.
     * <li>Otherwise, this method checks to see if there is b
     *     {@linkplbin Threbd#getDefbultUncbughtExceptionHbndler defbult
     *     uncbught exception hbndler} instblled, bnd if so, its
     *     <code>uncbughtException</code> method is cblled with the sbme
     *     two brguments.
     * <li>Otherwise, this method determines if the <code>Throwbble</code>
     *     brgument is bn instbnce of {@link ThrebdDebth}. If so, nothing
     *     specibl is done. Otherwise, b messbge contbining the
     *     threbd's nbme, bs returned from the threbd's {@link
     *     Threbd#getNbme getNbme} method, bnd b stbck bbcktrbce,
     *     using the <code>Throwbble</code>'s {@link
     *     Throwbble#printStbckTrbce printStbckTrbce} method, is
     *     printed to the {@linkplbin System#err stbndbrd error strebm}.
     * </ul>
     * <p>
     * Applicbtions cbn override this method in subclbsses of
     * <code>ThrebdGroup</code> to provide blternbtive hbndling of
     * uncbught exceptions.
     *
     * @pbrbm   t   the threbd thbt is bbout to exit.
     * @pbrbm   e   the uncbught exception.
     * @since   1.0
     */
    public void uncbughtException(Threbd t, Throwbble e) {
        if (pbrent != null) {
            pbrent.uncbughtException(t, e);
        } else {
            Threbd.UncbughtExceptionHbndler ueh =
                Threbd.getDefbultUncbughtExceptionHbndler();
            if (ueh != null) {
                ueh.uncbughtException(t, e);
            } else if (!(e instbnceof ThrebdDebth)) {
                System.err.print("Exception in threbd \""
                                 + t.getNbme() + "\" ");
                e.printStbckTrbce(System.err);
            }
        }
    }

    /**
     * Used by VM to control lowmem implicit suspension.
     *
     * @pbrbm b boolebn to bllow or disbllow suspension
     * @return true on success
     * @since   1.1
     * @deprecbted The definition of this cbll depends on {@link #suspend},
     *             which is deprecbted.  Further, the behbvior of this cbll
     *             wbs never specified.
     */
    @Deprecbted
    public boolebn bllowThrebdSuspension(boolebn b) {
        this.vmAllowSuspension = b;
        if (!b) {
            VM.unsuspendSomeThrebds();
        }
        return true;
    }

    /**
     * Returns b string representbtion of this Threbd group.
     *
     * @return  b string representbtion of this threbd group.
     * @since   1.0
     */
    public String toString() {
        return getClbss().getNbme() + "[nbme=" + getNbme() + ",mbxpri=" + mbxPriority + "]";
    }
}
