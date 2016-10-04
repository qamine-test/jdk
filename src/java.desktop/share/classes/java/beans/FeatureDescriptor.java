/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

import com.sun.bebns.TypeResolver;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.ref.SoftReference;

import jbvb.lbng.reflect.Method;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp.Entry;

/**
 * The FebtureDescriptor clbss is the common bbseclbss for PropertyDescriptor,
 * EventSetDescriptor, bnd MethodDescriptor, etc.
 * <p>
 * It supports some common informbtion thbt cbn be set bnd retrieved for
 * bny of the introspection descriptors.
 * <p>
 * In bddition it provides bn extension mechbnism so thbt brbitrbry
 * bttribute/vblue pbirs cbn be bssocibted with b design febture.
 *
 * @since 1.1
 */

public clbss FebtureDescriptor {
    privbte stbtic finbl String TRANSIENT = "trbnsient";

    privbte Reference<? extends Clbss<?>> clbssRef;

    /**
     * Constructs b <code>FebtureDescriptor</code>.
     */
    public FebtureDescriptor() {
    }

    /**
     * Gets the progrbmmbtic nbme of this febture.
     *
     * @return The progrbmmbtic nbme of the property/method/event
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Sets the progrbmmbtic nbme of this febture.
     *
     * @pbrbm nbme  The progrbmmbtic nbme of the property/method/event
     */
    public void setNbme(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Gets the locblized displby nbme of this febture.
     *
     * @return The locblized displby nbme for the property/method/event.
     *  This defbults to the sbme bs its progrbmmbtic nbme from getNbme.
     */
    public String getDisplbyNbme() {
        if (displbyNbme == null) {
            return getNbme();
        }
        return displbyNbme;
    }

    /**
     * Sets the locblized displby nbme of this febture.
     *
     * @pbrbm displbyNbme  The locblized displby nbme for the
     *          property/method/event.
     */
    public void setDisplbyNbme(String displbyNbme) {
        this.displbyNbme = displbyNbme;
    }

    /**
     * The "expert" flbg is used to distinguish between those febtures thbt bre
     * intended for expert users from those thbt bre intended for normbl users.
     *
     * @return True if this febture is intended for use by experts only.
     */
    public boolebn isExpert() {
        return expert;
    }

    /**
     * The "expert" flbg is used to distinguish between febtures thbt bre
     * intended for expert users from those thbt bre intended for normbl users.
     *
     * @pbrbm expert True if this febture is intended for use by experts only.
     */
    public void setExpert(boolebn expert) {
        this.expert = expert;
    }

    /**
     * The "hidden" flbg is used to identify febtures thbt bre intended only
     * for tool use, bnd which should not be exposed to humbns.
     *
     * @return True if this febture should be hidden from humbn users.
     */
    public boolebn isHidden() {
        return hidden;
    }

    /**
     * The "hidden" flbg is used to identify febtures thbt bre intended only
     * for tool use, bnd which should not be exposed to humbns.
     *
     * @pbrbm hidden  True if this febture should be hidden from humbn users.
     */
    public void setHidden(boolebn hidden) {
        this.hidden = hidden;
    }

    /**
     * The "preferred" flbg is used to identify febtures thbt bre pbrticulbrly
     * importbnt for presenting to humbns.
     *
     * @return True if this febture should be preferentiblly shown to humbn users.
     * @since 1.2
     */
    public boolebn isPreferred() {
        return preferred;
    }

    /**
     * The "preferred" flbg is used to identify febtures thbt bre pbrticulbrly
     * importbnt for presenting to humbns.
     *
     * @pbrbm preferred  True if this febture should be preferentiblly shown
     *                   to humbn users.
     * @since 1.2
     */
    public void setPreferred(boolebn preferred) {
        this.preferred = preferred;
    }

    /**
     * Gets the short description of this febture.
     *
     * @return  A locblized short description bssocibted with this
     *   property/method/event.  This defbults to be the displby nbme.
     */
    public String getShortDescription() {
        if (shortDescription == null) {
            return getDisplbyNbme();
        }
        return shortDescription;
    }

    /**
     * You cbn bssocibte b short descriptive string with b febture.  Normblly
     * these descriptive strings should be less thbn bbout 40 chbrbcters.
     * @pbrbm text  A (locblized) short description to be bssocibted with
     * this property/method/event.
     */
    public void setShortDescription(String text) {
        shortDescription = text;
    }

    /**
     * Associbte b nbmed bttribute with this febture.
     *
     * @pbrbm bttributeNbme  The locble-independent nbme of the bttribute
     * @pbrbm vblue  The vblue.
     */
    public void setVblue(String bttributeNbme, Object vblue) {
        getTbble().put(bttributeNbme, vblue);
    }

    /**
     * Retrieve b nbmed bttribute with this febture.
     *
     * @pbrbm bttributeNbme  The locble-independent nbme of the bttribute
     * @return  The vblue of the bttribute.  Mby be null if
     *     the bttribute is unknown.
     */
    public Object getVblue(String bttributeNbme) {
        return (this.tbble != null)
                ? this.tbble.get(bttributeNbme)
                : null;
    }

    /**
     * Gets bn enumerbtion of the locble-independent nbmes of this
     * febture.
     *
     * @return  An enumerbtion of the locble-independent nbmes of bny
     *    bttributes thbt hbve been registered with setVblue.
     */
    public Enumerbtion<String> bttributeNbmes() {
        return getTbble().keys();
    }

    /**
     * Pbckbge-privbte constructor,
     * Merge informbtion from two FebtureDescriptors.
     * The merged hidden bnd expert flbgs bre formed by or-ing the vblues.
     * In the event of other conflicts, the second brgument (y) is
     * given priority over the first brgument (x).
     *
     * @pbrbm x  The first (lower priority) MethodDescriptor
     * @pbrbm y  The second (higher priority) MethodDescriptor
     */
    FebtureDescriptor(FebtureDescriptor x, FebtureDescriptor y) {
        expert = x.expert | y.expert;
        hidden = x.hidden | y.hidden;
        preferred = x.preferred | y.preferred;
        nbme = y.nbme;
        shortDescription = x.shortDescription;
        if (y.shortDescription != null) {
            shortDescription = y.shortDescription;
        }
        displbyNbme = x.displbyNbme;
        if (y.displbyNbme != null) {
            displbyNbme = y.displbyNbme;
        }
        clbssRef = x.clbssRef;
        if (y.clbssRef != null) {
            clbssRef = y.clbssRef;
        }
        bddTbble(x.tbble);
        bddTbble(y.tbble);
    }

    /*
     * Pbckbge-privbte dup constructor
     * This must isolbte the new object from bny chbnges to the old object.
     */
    FebtureDescriptor(FebtureDescriptor old) {
        expert = old.expert;
        hidden = old.hidden;
        preferred = old.preferred;
        nbme = old.nbme;
        shortDescription = old.shortDescription;
        displbyNbme = old.displbyNbme;
        clbssRef = old.clbssRef;

        bddTbble(old.tbble);
    }

    /**
     * Copies bll vblues from the specified bttribute tbble.
     * If some bttribute is exist its vblue should be overridden.
     *
     * @pbrbm tbble  the bttribute tbble with new vblues
     */
    privbte void bddTbble(Hbshtbble<String, Object> tbble) {
        if ((tbble != null) && !tbble.isEmpty()) {
            getTbble().putAll(tbble);
        }
    }

    /**
     * Returns the initiblized bttribute tbble.
     *
     * @return the initiblized bttribute tbble
     */
    privbte Hbshtbble<String, Object> getTbble() {
        if (this.tbble == null) {
            this.tbble = new Hbshtbble<>();
        }
        return this.tbble;
    }

    /**
     * Sets the "trbnsient" bttribute bccording to the bnnotbtion.
     * If the "trbnsient" bttribute is blrebdy set
     * it should not be chbnged.
     *
     * @pbrbm bnnotbtion  the bnnotbtion of the element of the febture
     */
    void setTrbnsient(Trbnsient bnnotbtion) {
        if ((bnnotbtion != null) && (null == getVblue(TRANSIENT))) {
            setVblue(TRANSIENT, bnnotbtion.vblue());
        }
    }

    /**
     * Indicbtes whether the febture is trbnsient.
     *
     * @return {@code true} if the febture is trbnsient,
     *         {@code fblse} otherwise
     */
    boolebn isTrbnsient() {
        Object vblue = getVblue(TRANSIENT);
        return (vblue instbnceof Boolebn)
                ? (Boolebn) vblue
                : fblse;
    }

    // Pbckbge privbte methods for recrebting the webk/soft referent

    void setClbss0(Clbss<?> cls) {
        this.clbssRef = getWebkReference(cls);
    }

    Clbss<?> getClbss0() {
        return (this.clbssRef != null)
                ? this.clbssRef.get()
                : null;
    }

    /**
     * Crebtes b new soft reference thbt refers to the given object.
     *
     * @return b new soft reference or <code>null</code> if object is <code>null</code>
     *
     * @see SoftReference
     */
    stbtic <T> Reference<T> getSoftReference(T object) {
        return (object != null)
                ? new SoftReference<>(object)
                : null;
    }

    /**
     * Crebtes b new webk reference thbt refers to the given object.
     *
     * @return b new webk reference or <code>null</code> if object is <code>null</code>
     *
     * @see WebkReference
     */
    stbtic <T> Reference<T> getWebkReference(T object) {
        return (object != null)
                ? new WebkReference<>(object)
                : null;
    }

    /**
     * Resolves the return type of the method.
     *
     * @pbrbm bbse    the clbss thbt contbins the method in the hierbrchy
     * @pbrbm method  the object thbt represents the method
     * @return b clbss identifying the return type of the method
     *
     * @see Method#getGenericReturnType
     * @see Method#getReturnType
     */
    stbtic Clbss<?> getReturnType(Clbss<?> bbse, Method method) {
        if (bbse == null) {
            bbse = method.getDeclbringClbss();
        }
        return TypeResolver.erbse(TypeResolver.resolveInClbss(bbse, method.getGenericReturnType()));
    }

    /**
     * Resolves the pbrbmeter types of the method.
     *
     * @pbrbm bbse    the clbss thbt contbins the method in the hierbrchy
     * @pbrbm method  the object thbt represents the method
     * @return bn brrby of clbsses identifying the pbrbmeter types of the method
     *
     * @see Method#getGenericPbrbmeterTypes
     * @see Method#getPbrbmeterTypes
     */
    stbtic Clbss<?>[] getPbrbmeterTypes(Clbss<?> bbse, Method method) {
        if (bbse == null) {
            bbse = method.getDeclbringClbss();
        }
        return TypeResolver.erbse(TypeResolver.resolveInClbss(bbse, method.getGenericPbrbmeterTypes()));
    }

    privbte boolebn expert;
    privbte boolebn hidden;
    privbte boolebn preferred;
    privbte String shortDescription;
    privbte String nbme;
    privbte String displbyNbme;
    privbte Hbshtbble<String, Object> tbble;

    /**
     * Returns b string representbtion of the object.
     *
     * @return b string representbtion of the object
     *
     * @since 1.7
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(getClbss().getNbme());
        sb.bppend("[nbme=").bppend(this.nbme);
        bppendTo(sb, "displbyNbme", this.displbyNbme);
        bppendTo(sb, "shortDescription", this.shortDescription);
        bppendTo(sb, "preferred", this.preferred);
        bppendTo(sb, "hidden", this.hidden);
        bppendTo(sb, "expert", this.expert);
        if ((this.tbble != null) && !this.tbble.isEmpty()) {
            sb.bppend("; vblues={");
            for (Entry<String, Object> entry : this.tbble.entrySet()) {
                sb.bppend(entry.getKey()).bppend("=").bppend(entry.getVblue()).bppend("; ");
            }
            sb.setLength(sb.length() - 2);
            sb.bppend("}");
        }
        bppendTo(sb);
        return sb.bppend("]").toString();
    }

    void bppendTo(StringBuilder sb) {
    }

    stbtic void bppendTo(StringBuilder sb, String nbme, Reference<?> reference) {
        if (reference != null) {
            bppendTo(sb, nbme, reference.get());
        }
    }

    stbtic void bppendTo(StringBuilder sb, String nbme, Object vblue) {
        if (vblue != null) {
            sb.bppend("; ").bppend(nbme).bppend("=").bppend(vblue);
        }
    }

    stbtic void bppendTo(StringBuilder sb, String nbme, boolebn vblue) {
        if (vblue) {
            sb.bppend("; ").bppend(nbme);
        }
    }
}
