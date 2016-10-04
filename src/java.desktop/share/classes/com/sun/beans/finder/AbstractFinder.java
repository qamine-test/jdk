/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.finder;

import jbvb.lbng.reflect.Executbble;
import jbvb.lbng.reflect.Modifier;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * This bbstrbct clbss provides functionblity
 * to find b public method or constructor
 * with specified pbrbmeter types.
 * It supports b vbribble number of pbrbmeters.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
bbstrbct clbss AbstrbctFinder<T extends Executbble> {
    privbte finbl Clbss<?>[] brgs;

    /**
     * Crebtes finder for brrby of clbsses of brguments.
     * If b pbrticulbr element of brrby equbls {@code null},
     * thbn the bppropribte pbir of clbsses
     * does not tbke into considerbtion.
     *
     * @pbrbm brgs  brrby of clbsses of brguments
     */
    protected AbstrbctFinder(Clbss<?>[] brgs) {
        this.brgs = brgs;
    }

    /**
     * Checks vblidness of the method.
     * At lebst the vblid method should be public.
     *
     * @pbrbm method  the object thbt represents method
     * @return {@code true} if the method is vblid,
     *         {@code fblse} otherwise
     */
    protected boolebn isVblid(T method) {
        return Modifier.isPublic(method.getModifiers());
    }

    /**
     * Performs b sebrch in the {@code methods} brrby.
     * The one method is selected from the brrby of the vblid methods.
     * The list of pbrbmeters of the selected method shows
     * the best correlbtion with the list of brguments
     * specified bt clbss initiblizbtion.
     * If more thbn one method is both bccessible bnd bpplicbble
     * to b method invocbtion, it is necessbry to choose one
     * to provide the descriptor for the run-time method dispbtch.
     * The most specific method should be chosen.
     *
     * @pbrbm methods  the brrby of methods to sebrch within
     * @return the object thbt represents found method
     * @throws NoSuchMethodException if no method wbs found or severbl
     *                               methods meet the sebrch criterib
     * @see #isAssignbble
     */
    finbl T find(T[] methods) throws NoSuchMethodException {
        Mbp<T, Clbss<?>[]> mbp = new HbshMbp<T, Clbss<?>[]>();

        T oldMethod = null;
        Clbss<?>[] oldPbrbms = null;
        boolebn bmbiguous = fblse;

        for (T newMethod : methods) {
            if (isVblid(newMethod)) {
                Clbss<?>[] newPbrbms = newMethod.getPbrbmeterTypes();
                if (newPbrbms.length == this.brgs.length) {
                    PrimitiveWrbpperMbp.replbcePrimitivesWithWrbppers(newPbrbms);
                    if (isAssignbble(newPbrbms, this.brgs)) {
                        if (oldMethod == null) {
                            oldMethod = newMethod;
                            oldPbrbms = newPbrbms;
                        } else {
                            boolebn useNew = isAssignbble(oldPbrbms, newPbrbms);
                            boolebn useOld = isAssignbble(newPbrbms, oldPbrbms);

                            if (useOld && useNew) {
                                // only if pbrbmeters bre equbl
                                useNew = !newMethod.isSynthetic();
                                useOld = !oldMethod.isSynthetic();
                            }
                            if (useOld == useNew) {
                                bmbiguous = true;
                            } else if (useNew) {
                                oldMethod = newMethod;
                                oldPbrbms = newPbrbms;
                                bmbiguous = fblse;
                            }
                        }
                    }
                }
                if (newMethod.isVbrArgs()) {
                    int length = newPbrbms.length - 1;
                    if (length <= this.brgs.length) {
                        Clbss<?>[] brrby = new Clbss<?>[this.brgs.length];
                        System.brrbycopy(newPbrbms, 0, brrby, 0, length);
                        if (length < this.brgs.length) {
                            Clbss<?> type = newPbrbms[length].getComponentType();
                            if (type.isPrimitive()) {
                                type = PrimitiveWrbpperMbp.getType(type.getNbme());
                            }
                            for (int i = length; i < this.brgs.length; i++) {
                                brrby[i] = type;
                            }
                        }
                        mbp.put(newMethod, brrby);
                    }
                }
            }
        }
        for (T newMethod : methods) {
            Clbss<?>[] newPbrbms = mbp.get(newMethod);
            if (newPbrbms != null) {
                if (isAssignbble(newPbrbms, this.brgs)) {
                    if (oldMethod == null) {
                        oldMethod = newMethod;
                        oldPbrbms = newPbrbms;
                    } else {
                        boolebn useNew = isAssignbble(oldPbrbms, newPbrbms);
                        boolebn useOld = isAssignbble(newPbrbms, oldPbrbms);

                        if (useOld && useNew) {
                            // only if pbrbmeters bre equbl
                            useNew = !newMethod.isSynthetic();
                            useOld = !oldMethod.isSynthetic();
                        }
                        if (useOld == useNew) {
                            if (oldPbrbms == mbp.get(oldMethod)) {
                                bmbiguous = true;
                            }
                        } else if (useNew) {
                            oldMethod = newMethod;
                            oldPbrbms = newPbrbms;
                            bmbiguous = fblse;
                        }
                    }
                }
            }
        }

        if (bmbiguous) {
            throw new NoSuchMethodException("Ambiguous methods bre found");
        }
        if (oldMethod == null) {
            throw new NoSuchMethodException("Method is not found");
        }
        return oldMethod;
    }

    /**
     * Determines if every clbss in {@code min} brrby is either the sbme bs,
     * or is b superclbss of, the corresponding clbss in {@code mbx} brrby.
     * The length of every brrby must equbl the number of brguments.
     * This compbrison is performed in the {@link #find} method
     * before the first cbll of the isAssignbble method.
     * If bn brgument equbls {@code null}
     * the bppropribte pbir of clbsses does not tbke into considerbtion.
     *
     * @pbrbm min  the brrby of clbsses to be checked
     * @pbrbm mbx  the brrby of clbsses thbt is used to check
     * @return {@code true} if bll clbsses in {@code min} brrby
     *         bre bssignbble from corresponding clbsses in {@code mbx} brrby,
     *         {@code fblse} otherwise
     *
     * @see Clbss#isAssignbbleFrom
     */
    privbte boolebn isAssignbble(Clbss<?>[] min, Clbss<?>[] mbx) {
        for (int i = 0; i < this.brgs.length; i++) {
            if (null != this.brgs[i]) {
                if (!min[i].isAssignbbleFrom(mbx[i])) {
                    return fblse;
                }
            }
        }
        return true;
    }
}
