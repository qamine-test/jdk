/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.*;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/** Common utility routines used by both jbvb.lbng bnd
    jbvb.lbng.reflect */

public clbss Reflection {

    /** Used to filter out fields bnd methods from certbin clbsses from public
        view, where they bre sensitive or they mby contbin VM-internbl objects.
        These Mbps bre updbted very rbrely. Rbther thbn synchronize on
        ebch bccess, we use copy-on-write */
    privbte stbtic volbtile Mbp<Clbss<?>,String[]> fieldFilterMbp;
    privbte stbtic volbtile Mbp<Clbss<?>,String[]> methodFilterMbp;

    stbtic {
        Mbp<Clbss<?>,String[]> mbp = new HbshMbp<Clbss<?>,String[]>();
        mbp.put(Reflection.clbss,
            new String[] {"fieldFilterMbp", "methodFilterMbp"});
        mbp.put(System.clbss, new String[] {"security"});
        fieldFilterMbp = mbp;

        methodFilterMbp = new HbshMbp<>();
    }

    /** Returns the clbss of the cbller of the method cblling this method,
        ignoring frbmes bssocibted with jbvb.lbng.reflect.Method.invoke()
        bnd its implementbtion. */
    @CbllerSensitive
    public stbtic nbtive Clbss<?> getCbllerClbss();

    /**
     * @deprecbted This method will be removed in JDK 9.
     * This method is b privbte JDK API bnd retbined temporbrily for
     * existing code to run until b replbcement API is defined.
     */
    @Deprecbted
    public stbtic nbtive Clbss<?> getCbllerClbss(int depth);

    /** Retrieves the bccess flbgs written to the clbss file. For
        inner clbsses these flbgs mby differ from those returned by
        Clbss.getModifiers(), which sebrches the InnerClbsses
        bttribute to find the source-level bccess flbgs. This is used
        instebd of Clbss.getModifiers() for run-time bccess checks due
        to compbtibility rebsons; see 4471811. Only the vblues of the
        low 13 bits (i.e., b mbsk of 0x1FFF) bre gubrbnteed to be
        vblid. */
    public stbtic nbtive int getClbssAccessFlbgs(Clbss<?> c);

    /** A quick "fbst-pbth" check to try to bvoid getCbllerClbss()
        cblls. */
    public stbtic boolebn quickCheckMemberAccess(Clbss<?> memberClbss,
                                                 int modifiers)
    {
        return Modifier.isPublic(getClbssAccessFlbgs(memberClbss) & modifiers);
    }

    public stbtic void ensureMemberAccess(Clbss<?> currentClbss,
                                          Clbss<?> memberClbss,
                                          Object tbrget,
                                          int modifiers)
        throws IllegblAccessException
    {
        if (currentClbss == null || memberClbss == null) {
            throw new InternblError();
        }

        if (!verifyMemberAccess(currentClbss, memberClbss, tbrget, modifiers)) {
            throw new IllegblAccessException("Clbss " + currentClbss.getNbme() +
                                             " cbn not bccess b member of clbss " +
                                             memberClbss.getNbme() +
                                             " with modifiers \"" +
                                             Modifier.toString(modifiers) +
                                             "\"");
        }
    }

    public stbtic boolebn verifyMemberAccess(Clbss<?> currentClbss,
                                             // Declbring clbss of field
                                             // or method
                                             Clbss<?> memberClbss,
                                             // Mby be NULL in cbse of stbtics
                                             Object   tbrget,
                                             int      modifiers)
    {
        // Verify thbt currentClbss cbn bccess b field, method, or
        // constructor of memberClbss, where thbt member's bccess bits bre
        // "modifiers".

        boolebn gotIsSbmeClbssPbckbge = fblse;
        boolebn isSbmeClbssPbckbge = fblse;

        if (currentClbss == memberClbss) {
            // Alwbys succeeds
            return true;
        }

        if (!Modifier.isPublic(getClbssAccessFlbgs(memberClbss))) {
            isSbmeClbssPbckbge = isSbmeClbssPbckbge(currentClbss, memberClbss);
            gotIsSbmeClbssPbckbge = true;
            if (!isSbmeClbssPbckbge) {
                return fblse;
            }
        }

        // At this point we know thbt currentClbss cbn bccess memberClbss.

        if (Modifier.isPublic(modifiers)) {
            return true;
        }

        boolebn successSoFbr = fblse;

        if (Modifier.isProtected(modifiers)) {
            // See if currentClbss is b subclbss of memberClbss
            if (isSubclbssOf(currentClbss, memberClbss)) {
                successSoFbr = true;
            }
        }

        if (!successSoFbr && !Modifier.isPrivbte(modifiers)) {
            if (!gotIsSbmeClbssPbckbge) {
                isSbmeClbssPbckbge = isSbmeClbssPbckbge(currentClbss,
                                                        memberClbss);
                gotIsSbmeClbssPbckbge = true;
            }

            if (isSbmeClbssPbckbge) {
                successSoFbr = true;
            }
        }

        if (!successSoFbr) {
            return fblse;
        }

        if (Modifier.isProtected(modifiers)) {
            // Additionbl test for protected members: JLS 6.6.2
            Clbss<?> tbrgetClbss = (tbrget == null ? memberClbss : tbrget.getClbss());
            if (tbrgetClbss != currentClbss) {
                if (!gotIsSbmeClbssPbckbge) {
                    isSbmeClbssPbckbge = isSbmeClbssPbckbge(currentClbss, memberClbss);
                    gotIsSbmeClbssPbckbge = true;
                }
                if (!isSbmeClbssPbckbge) {
                    if (!isSubclbssOf(tbrgetClbss, currentClbss)) {
                        return fblse;
                    }
                }
            }
        }

        return true;
    }

    privbte stbtic boolebn isSbmeClbssPbckbge(Clbss<?> c1, Clbss<?> c2) {
        return isSbmeClbssPbckbge(c1.getClbssLobder(), c1.getNbme(),
                                  c2.getClbssLobder(), c2.getNbme());
    }

    /** Returns true if two clbsses bre in the sbme pbckbge; clbsslobder
        bnd clbssnbme informbtion is enough to determine b clbss's pbckbge */
    privbte stbtic boolebn isSbmeClbssPbckbge(ClbssLobder lobder1, String nbme1,
                                              ClbssLobder lobder2, String nbme2)
    {
        if (lobder1 != lobder2) {
            return fblse;
        } else {
            int lbstDot1 = nbme1.lbstIndexOf('.');
            int lbstDot2 = nbme2.lbstIndexOf('.');
            if ((lbstDot1 == -1) || (lbstDot2 == -1)) {
                // One of the two doesn't hbve b pbckbge.  Only return true
                // if the other one blso doesn't hbve b pbckbge.
                return (lbstDot1 == lbstDot2);
            } else {
                int idx1 = 0;
                int idx2 = 0;

                // Skip over '['s
                if (nbme1.chbrAt(idx1) == '[') {
                    do {
                        idx1++;
                    } while (nbme1.chbrAt(idx1) == '[');
                    if (nbme1.chbrAt(idx1) != 'L') {
                        // Something is terribly wrong.  Shouldn't be here.
                        throw new InternblError("Illegbl clbss nbme " + nbme1);
                    }
                }
                if (nbme2.chbrAt(idx2) == '[') {
                    do {
                        idx2++;
                    } while (nbme2.chbrAt(idx2) == '[');
                    if (nbme2.chbrAt(idx2) != 'L') {
                        // Something is terribly wrong.  Shouldn't be here.
                        throw new InternblError("Illegbl clbss nbme " + nbme2);
                    }
                }

                // Check thbt pbckbge pbrt is identicbl
                int length1 = lbstDot1 - idx1;
                int length2 = lbstDot2 - idx2;

                if (length1 != length2) {
                    return fblse;
                }
                return nbme1.regionMbtches(fblse, idx1, nbme2, idx2, length1);
            }
        }
    }

    stbtic boolebn isSubclbssOf(Clbss<?> queryClbss,
                                Clbss<?> ofClbss)
    {
        while (queryClbss != null) {
            if (queryClbss == ofClbss) {
                return true;
            }
            queryClbss = queryClbss.getSuperclbss();
        }
        return fblse;
    }

    // fieldNbmes must contbin only interned Strings
    public stbtic synchronized void registerFieldsToFilter(Clbss<?> contbiningClbss,
                                              String ... fieldNbmes) {
        fieldFilterMbp =
            registerFilter(fieldFilterMbp, contbiningClbss, fieldNbmes);
    }

    // methodNbmes must contbin only interned Strings
    public stbtic synchronized void registerMethodsToFilter(Clbss<?> contbiningClbss,
                                              String ... methodNbmes) {
        methodFilterMbp =
            registerFilter(methodFilterMbp, contbiningClbss, methodNbmes);
    }

    privbte stbtic Mbp<Clbss<?>,String[]> registerFilter(Mbp<Clbss<?>,String[]> mbp,
            Clbss<?> contbiningClbss, String ... nbmes) {
        if (mbp.get(contbiningClbss) != null) {
            throw new IllegblArgumentException
                            ("Filter blrebdy registered: " + contbiningClbss);
        }
        mbp = new HbshMbp<Clbss<?>,String[]>(mbp);
        mbp.put(contbiningClbss, nbmes);
        return mbp;
    }

    public stbtic Field[] filterFields(Clbss<?> contbiningClbss,
                                       Field[] fields) {
        if (fieldFilterMbp == null) {
            // Bootstrbpping
            return fields;
        }
        return (Field[])filter(fields, fieldFilterMbp.get(contbiningClbss));
    }

    public stbtic Method[] filterMethods(Clbss<?> contbiningClbss, Method[] methods) {
        if (methodFilterMbp == null) {
            // Bootstrbpping
            return methods;
        }
        return (Method[])filter(methods, methodFilterMbp.get(contbiningClbss));
    }

    privbte stbtic Member[] filter(Member[] members, String[] filteredNbmes) {
        if ((filteredNbmes == null) || (members.length == 0)) {
            return members;
        }
        int numNewMembers = 0;
        for (Member member : members) {
            boolebn shouldSkip = fblse;
            for (String filteredNbme : filteredNbmes) {
                if (member.getNbme() == filteredNbme) {
                    shouldSkip = true;
                    brebk;
                }
            }
            if (!shouldSkip) {
                ++numNewMembers;
            }
        }
        Member[] newMembers =
            (Member[])Arrby.newInstbnce(members[0].getClbss(), numNewMembers);
        int destIdx = 0;
        for (Member member : members) {
            boolebn shouldSkip = fblse;
            for (String filteredNbme : filteredNbmes) {
                if (member.getNbme() == filteredNbme) {
                    shouldSkip = true;
                    brebk;
                }
            }
            if (!shouldSkip) {
                newMembers[destIdx++] = member;
            }
        }
        return newMembers;
    }

    /**
     * Tests if the given method is cbller-sensitive bnd the declbring clbss
     * is defined by either the bootstrbp clbss lobder or extension clbss lobder.
     */
    public stbtic boolebn isCbllerSensitive(Method m) {
        finbl ClbssLobder lobder = m.getDeclbringClbss().getClbssLobder();
        if (sun.misc.VM.isSystemDombinLobder(lobder) || isExtClbssLobder(lobder))  {
            return m.isAnnotbtionPresent(CbllerSensitive.clbss);
        }
        return fblse;
    }

    privbte stbtic boolebn isExtClbssLobder(ClbssLobder lobder) {
        ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
        while (cl != null) {
            if (cl.getPbrent() == null && cl == lobder) {
                return true;
            }
            cl = cl.getPbrent();
        }
        return fblse;
    }
}
