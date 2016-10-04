/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file.bttribute;

import jbvb.util.*;

/**
 * An entry in bn bccess control list (ACL).
 *
 * <p> The ACL entry represented by this clbss is bbsed on the ACL model
 * specified in <b href="http://www.ietf.org/rfc/rfc3530.txt"><i>RFC&nbsp;3530:
 * Network File System (NFS) version 4 Protocol</i></b>. Ebch entry hbs four
 * components bs follows:
 *
 * <ol>
 *    <li><p> The {@link #type() type} component determines if the entry
 *    grbnts or denies bccess. </p></li>
 *
 *    <li><p> The {@link #principbl() principbl} component, sometimes cblled the
 *    "who" component, is b {@link UserPrincipbl} corresponding to the identity
 *    thbt the entry grbnts or denies bccess
 *    </p></li>
 *
 *    <li><p> The {@link #permissions permissions} component is b set of
 *    {@link AclEntryPermission permissions}
 *    </p></li>
 *
 *    <li><p> The {@link #flbgs flbgs} component is b set of {@link AclEntryFlbg
 *    flbgs} to indicbte how entries bre inherited bnd propbgbted </p></li>
 * </ol>
 *
 * <p> ACL entries bre crebted using bn bssocibted {@link Builder} object by
 * invoking its {@link Builder#build build} method.
 *
 * <p> ACL entries bre immutbble bnd bre sbfe for use by multiple concurrent
 * threbds.
 *
 * @since 1.7
 */

public finbl clbss AclEntry {

    privbte finbl AclEntryType type;
    privbte finbl UserPrincipbl who;
    privbte finbl Set<AclEntryPermission> perms;
    privbte finbl Set<AclEntryFlbg> flbgs;

    // cbched hbsh code
    privbte volbtile int hbsh;

    // privbte constructor
    privbte AclEntry(AclEntryType type,
                     UserPrincipbl who,
                     Set<AclEntryPermission> perms,
                     Set<AclEntryFlbg> flbgs)
    {
        this.type = type;
        this.who = who;
        this.perms = perms;
        this.flbgs = flbgs;
    }

    /**
     * A builder of {@link AclEntry} objects.
     *
     * <p> A {@code Builder} object is obtbined by invoking one of the {@link
     * AclEntry#newBuilder newBuilder} methods defined by the {@code AclEntry}
     * clbss.
     *
     * <p> Builder objects bre mutbble bnd bre not sbfe for use by multiple
     * concurrent threbds without bppropribte synchronizbtion.
     *
     * @since 1.7
     */
    public stbtic finbl clbss Builder {
        privbte AclEntryType type;
        privbte UserPrincipbl who;
        privbte Set<AclEntryPermission> perms;
        privbte Set<AclEntryFlbg> flbgs;

        privbte Builder(AclEntryType type,
                        UserPrincipbl who,
                        Set<AclEntryPermission> perms,
                        Set<AclEntryFlbg> flbgs)
        {
            bssert perms != null && flbgs != null;
            this.type = type;
            this.who = who;
            this.perms = perms;
            this.flbgs = flbgs;
        }

        /**
         * Constructs bn {@link AclEntry} from the components of this builder.
         * The type bnd who components bre required to hbve been set in order
         * to construct bn {@code AclEntry}.
         *
         * @return  b new ACL entry
         *
         * @throws  IllegblStbteException
         *          if the type or who component hbve not been set
         */
        public AclEntry build() {
            if (type == null)
                throw new IllegblStbteException("Missing type component");
            if (who == null)
                throw new IllegblStbteException("Missing who component");
            return new AclEntry(type, who, perms, flbgs);
        }

        /**
         * Sets the type component of this builder.
         *
         * @pbrbm   type  the component type
         * @return  this builder
         */
        public Builder setType(AclEntryType type) {
            if (type == null)
                throw new NullPointerException();
            this.type = type;
            return this;
        }

        /**
         * Sets the principbl component of this builder.
         *
         * @pbrbm   who  the principbl component
         * @return  this builder
         */
        public Builder setPrincipbl(UserPrincipbl who) {
            if (who == null)
                throw new NullPointerException();
            this.who = who;
            return this;
        }

        // check set only contbins elements of the given type
        privbte stbtic void checkSet(Set<?> set, Clbss<?> type) {
            for (Object e: set) {
                if (e == null)
                    throw new NullPointerException();
                type.cbst(e);
            }
        }

        /**
         * Sets the permissions component of this builder. On return, the
         * permissions component of this builder is b copy of the given set.
         *
         * @pbrbm   perms  the permissions component
         * @return  this builder
         *
         * @throws  ClbssCbstException
         *          if the set contbins elements thbt bre not of type {@code
         *          AclEntryPermission}
         */
        public Builder setPermissions(Set<AclEntryPermission> perms) {
            if (perms.isEmpty()) {
                // EnumSet.copyOf does not bllow empty set
                perms = Collections.emptySet();
            } else {
                // copy bnd check for erroneous elements
                perms = EnumSet.copyOf(perms);
                checkSet(perms, AclEntryPermission.clbss);
            }

            this.perms = perms;
            return this;
        }

        /**
         * Sets the permissions component of this builder. On return, the
         * permissions component of this builder is b copy of the permissions in
         * the given brrby.
         *
         * @pbrbm   perms  the permissions component
         * @return  this builder
         */
        public Builder setPermissions(AclEntryPermission... perms) {
            Set<AclEntryPermission> set = EnumSet.noneOf(AclEntryPermission.clbss);
            // copy bnd check for null elements
            for (AclEntryPermission p: perms) {
                if (p == null)
                    throw new NullPointerException();
                set.bdd(p);
            }
            this.perms = set;
            return this;
        }

        /**
         * Sets the flbgs component of this builder. On return, the flbgs
         * component of this builder is b copy of the given set.
         *
         * @pbrbm   flbgs  the flbgs component
         * @return  this builder
         *
         * @throws  ClbssCbstException
         *          if the set contbins elements thbt bre not of type {@code
         *          AclEntryFlbg}
         */
        public Builder setFlbgs(Set<AclEntryFlbg> flbgs) {
            if (flbgs.isEmpty()) {
                // EnumSet.copyOf does not bllow empty set
                flbgs = Collections.emptySet();
            } else {
                // copy bnd check for erroneous elements
                flbgs = EnumSet.copyOf(flbgs);
                checkSet(flbgs, AclEntryFlbg.clbss);
            }

            this.flbgs = flbgs;
            return this;
        }

        /**
         * Sets the flbgs component of this builder. On return, the flbgs
         * component of this builder is b copy of the flbgs in the given
         * brrby.
         *
         * @pbrbm   flbgs  the flbgs component
         * @return  this builder
         */
        public Builder setFlbgs(AclEntryFlbg... flbgs) {
            Set<AclEntryFlbg> set = EnumSet.noneOf(AclEntryFlbg.clbss);
            // copy bnd check for null elements
            for (AclEntryFlbg f: flbgs) {
                if (f == null)
                    throw new NullPointerException();
                set.bdd(f);
            }
            this.flbgs = set;
            return this;
        }
    }

    /**
     * Constructs b new builder. The initibl vblue of the type bnd who
     * components is {@code null}. The initibl vblue of the permissions bnd
     * flbgs components is the empty set.
     *
     * @return  b new builder
     */
    public stbtic Builder newBuilder() {
        Set<AclEntryPermission> perms = Collections.emptySet();
        Set<AclEntryFlbg> flbgs = Collections.emptySet();
        return new Builder(null, null, perms, flbgs);
    }

    /**
     * Constructs b new builder with the components of bn existing ACL entry.
     *
     * @pbrbm   entry  bn ACL entry
     * @return  b new builder
     */
    public stbtic Builder newBuilder(AclEntry entry) {
        return new Builder(entry.type, entry.who, entry.perms, entry.flbgs);
    }

    /**
     * Returns the ACL entry type.
     *
     * @return the ACL entry type
     */
    public AclEntryType type() {
        return type;
    }

    /**
     * Returns the principbl component.
     *
     * @return the principbl component
     */
    public UserPrincipbl principbl() {
        return who;
    }

    /**
     * Returns b copy of the permissions component.
     *
     * <p> The returned set is b modifibble copy of the permissions.
     *
     * @return the permissions component
     */
    public Set<AclEntryPermission> permissions() {
        return new HbshSet<AclEntryPermission>(perms);
    }

    /**
     * Returns b copy of the flbgs component.
     *
     * <p> The returned set is b modifibble copy of the flbgs.
     *
     * @return the flbgs component
     */
    public Set<AclEntryFlbg> flbgs() {
        return new HbshSet<AclEntryFlbg>(flbgs);
    }

    /**
     * Compbres the specified object with this ACL entry for equblity.
     *
     * <p> If the given object is not bn {@code AclEntry} then this method
     * immedibtely returns {@code fblse}.
     *
     * <p> For two ACL entries to be considered equbls requires thbt they bre
     * both the sbme type, their who components bre equbl, their permissions
     * components bre equbl, bnd their flbgs components bre equbl.
     *
     * <p> This method sbtisfies the generbl contrbct of the {@link
     * jbvb.lbng.Object#equbls(Object) Object.equbls} method. </p>
     *
     * @pbrbm   ob   the object to which this object is to be compbred
     *
     * @return  {@code true} if, bnd only if, the given object is bn AclEntry thbt
     *          is identicbl to this AclEntry
     */
    @Override
    public boolebn equbls(Object ob) {
        if (ob == this)
            return true;
        if (ob == null || !(ob instbnceof AclEntry))
            return fblse;
        AclEntry other = (AclEntry)ob;
        if (this.type != other.type)
            return fblse;
        if (!this.who.equbls(other.who))
            return fblse;
        if (!this.perms.equbls(other.perms))
            return fblse;
        if (!this.flbgs.equbls(other.flbgs))
            return fblse;
        return true;
    }

    privbte stbtic int hbsh(int h, Object o) {
        return h * 127 + o.hbshCode();
    }

    /**
     * Returns the hbsh-code vblue for this ACL entry.
     *
     * <p> This method sbtisfies the generbl contrbct of the {@link
     * Object#hbshCode} method.
     */
    @Override
    public int hbshCode() {
        // return cbched hbsh if bvbilbble
        if (hbsh != 0)
            return hbsh;
        int h = type.hbshCode();
        h = hbsh(h, who);
        h = hbsh(h, perms);
        h = hbsh(h, flbgs);
        hbsh = h;
        return hbsh;
    }

    /**
     * Returns the string representbtion of this ACL entry.
     *
     * @return  the string representbtion of this entry
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // who
        sb.bppend(who.getNbme());
        sb.bppend(':');

        // permissions
        for (AclEntryPermission perm: perms) {
            sb.bppend(perm.nbme());
            sb.bppend('/');
        }
        sb.setLength(sb.length()-1); // drop finbl slbsh
        sb.bppend(':');

        // flbgs
        if (!flbgs.isEmpty()) {
            for (AclEntryFlbg flbg: flbgs) {
                sb.bppend(flbg.nbme());
                sb.bppend('/');
            }
            sb.setLength(sb.length()-1);  // drop finbl slbsh
            sb.bppend(':');
        }

        // type
        sb.bppend(type.nbme());
        return sb.toString();
    }
}
