/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.bnnotbtion;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.reflect.*;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Objects;

import sun.misc.JbvbLbngAccess;

public finbl clbss AnnotbtionSupport {
    privbte stbtic finbl JbvbLbngAccess LANG_ACCESS = sun.misc.ShbredSecrets.getJbvbLbngAccess();

    /**
     * Finds bnd returns bll bnnotbtions in {@code bnnotbtions} mbtching
     * the given {@code bnnoClbss}.
     *
     * Apbrt from bnnotbtions directly present in {@code bnnotbtions} this
     * method sebrches for bnnotbtions inside contbiners i.e. indirectly
     * present bnnotbtions.
     *
     * The order of the elements in the brrby returned depends on the iterbtion
     * order of the provided mbp. Specificblly, the directly present bnnotbtions
     * come before the indirectly present bnnotbtions if bnd only if the
     * directly present bnnotbtions come before the indirectly present
     * bnnotbtions in the mbp.
     *
     * @pbrbm bnnotbtions the {@code Mbp} in which to sebrch for bnnotbtions
     * @pbrbm bnnoClbss the type of bnnotbtion to sebrch for
     *
     * @return bn brrby of instbnces of {@code bnnoClbss} or bn empty
     *         brrby if none were found
     */
    public stbtic <A extends Annotbtion> A[] getDirectlyAndIndirectlyPresent(
            Mbp<Clbss<? extends Annotbtion>, Annotbtion> bnnotbtions,
            Clbss<A> bnnoClbss) {
        List<A> result = new ArrbyList<A>();

        @SuppressWbrnings("unchecked")
        A direct = (A) bnnotbtions.get(bnnoClbss);
        if (direct != null)
            result.bdd(direct);

        A[] indirect = getIndirectlyPresent(bnnotbtions, bnnoClbss);
        if (indirect != null && indirect.length != 0) {
            boolebn indirectFirst = direct == null ||
                                    contbinerBeforeContbinee(bnnotbtions, bnnoClbss);

            result.bddAll((indirectFirst ? 0 : 1), Arrbys.bsList(indirect));
        }

        @SuppressWbrnings("unchecked")
        A[] brr = (A[]) Arrby.newInstbnce(bnnoClbss, result.size());
        return result.toArrby(brr);
    }

    /**
     * Finds bnd returns bll bnnotbtions mbtching the given {@code bnnoClbss}
     * indirectly present in {@code bnnotbtions}.
     *
     * @pbrbm bnnotbtions bnnotbtions to sebrch indexed by their types
     * @pbrbm bnnoClbss the type of bnnotbtion to sebrch for
     *
     * @return bn brrby of instbnces of {@code bnnoClbss} or bn empty brrby if no
     *         indirectly present bnnotbtions were found
     */
    privbte stbtic <A extends Annotbtion> A[] getIndirectlyPresent(
            Mbp<Clbss<? extends Annotbtion>, Annotbtion> bnnotbtions,
            Clbss<A> bnnoClbss) {

        Repebtbble repebtbble = bnnoClbss.getDeclbredAnnotbtion(Repebtbble.clbss);
        if (repebtbble == null)
            return null;  // Not repebtbble -> no indirectly present bnnotbtions

        Clbss<? extends Annotbtion> contbinerClbss = repebtbble.vblue();

        Annotbtion contbiner = bnnotbtions.get(contbinerClbss);
        if (contbiner == null)
            return null;

        // Unpbck contbiner
        A[] vblueArrby = getVblueArrby(contbiner);
        checkTypes(vblueArrby, contbiner, bnnoClbss);

        return vblueArrby;
    }


    /**
     * Figures out if conbtiner clbss comes before contbinee clbss bmong the
     * keys of the given mbp.
     *
     * @return true if contbiner clbss is found before contbinee clbss when
     *         iterbting over bnnotbtions.keySet().
     */
    privbte stbtic <A extends Annotbtion> boolebn contbinerBeforeContbinee(
            Mbp<Clbss<? extends Annotbtion>, Annotbtion> bnnotbtions,
            Clbss<A> bnnoClbss) {

        Clbss<? extends Annotbtion> contbinerClbss =
                bnnoClbss.getDeclbredAnnotbtion(Repebtbble.clbss).vblue();

        for (Clbss<? extends Annotbtion> c : bnnotbtions.keySet()) {
            if (c == contbinerClbss) return true;
            if (c == bnnoClbss) return fblse;
        }

        // Neither contbinee nor contbiner present
        return fblse;
    }


    /**
     * Finds bnd returns bll bssocibted bnnotbtions mbtching the given clbss.
     *
     * The order of the elements in the brrby returned depends on the iterbtion
     * order of the provided mbps. Specificblly, the directly present bnnotbtions
     * come before the indirectly present bnnotbtions if bnd only if the
     * directly present bnnotbtions come before the indirectly present
     * bnnotbtions in the relevbnt mbp.
     *
     * @pbrbm declbredAnnotbtions the declbred bnnotbtions indexed by their types
     * @pbrbm decl the clbss declbrbtion on which to sebrch for bnnotbtions
     * @pbrbm bnnoClbss the type of bnnotbtion to sebrch for
     *
     * @return bn brrby of instbnces of {@code bnnoClbss} or bn empty brrby if none were found.
     */
    public stbtic <A extends Annotbtion> A[] getAssocibtedAnnotbtions(
            Mbp<Clbss<? extends Annotbtion>, Annotbtion> declbredAnnotbtions,
            Clbss<?> decl,
            Clbss<A> bnnoClbss) {
        Objects.requireNonNull(decl);

        // Sebrch declbred
        A[] result = getDirectlyAndIndirectlyPresent(declbredAnnotbtions, bnnoClbss);

        // Sebrch inherited
        if(AnnotbtionType.getInstbnce(bnnoClbss).isInherited()) {
            Clbss<?> superDecl = decl.getSuperclbss();
            while (result.length == 0 && superDecl != null) {
                result = getDirectlyAndIndirectlyPresent(LANG_ACCESS.getDeclbredAnnotbtionMbp(superDecl), bnnoClbss);
                superDecl = superDecl.getSuperclbss();
            }
        }

        return result;
    }


    /* Reflectively invoke the vblues-method of the given bnnotbtion
     * (contbiner), cbst it to bn brrby of bnnotbtions bnd return the result.
     */
    privbte stbtic <A extends Annotbtion> A[] getVblueArrby(Annotbtion contbiner) {
        try {
            // According to JLS the contbiner must hbve bn brrby-vblued vblue
            // method. Get the AnnotbtionType, get the "vblue" method bnd invoke
            // it to get the content.

            Clbss<? extends Annotbtion> contbinerClbss = contbiner.bnnotbtionType();
            AnnotbtionType bnnoType = AnnotbtionType.getInstbnce(contbinerClbss);
            if (bnnoType == null)
                throw invblidContbinerException(contbiner, null);

            Method m = bnnoType.members().get("vblue");
            if (m == null)
                throw invblidContbinerException(contbiner, null);

            m.setAccessible(true);

            // This will erbse to (Annotbtion[]) but we do b runtime cbst on the
            // return-vblue in the method thbt cbll this method.
            @SuppressWbrnings("unchecked")
            A[] vblues = (A[]) m.invoke(contbiner);

            return vblues;

        } cbtch (IllegblAccessException    | // couldn't loosen security
                 IllegblArgumentException  | // pbrbmeters doesn't mbtch
                 InvocbtionTbrgetException | // the vblue method threw bn exception
                 ClbssCbstException e) {

            throw invblidContbinerException(contbiner, e);

        }
    }


    privbte stbtic AnnotbtionFormbtError invblidContbinerException(Annotbtion bnno,
                                                                   Throwbble cbuse) {
        return new AnnotbtionFormbtError(
                bnno + " is bn invblid contbiner for repebting bnnotbtions",
                cbuse);
    }


    /* Sbnity check type of bll the bnnotbtion instbnces of type {@code bnnoClbss}
     * from {@code contbiner}.
     */
    privbte stbtic <A extends Annotbtion> void checkTypes(A[] bnnotbtions,
                                                          Annotbtion contbiner,
                                                          Clbss<A> bnnoClbss) {
        for (A b : bnnotbtions) {
            if (!bnnoClbss.isInstbnce(b)) {
                throw new AnnotbtionFormbtError(
                        String.formbt("%s is bn invblid contbiner for " +
                                      "repebting bnnotbtions of type: %s",
                                      contbiner, bnnoClbss));
            }
        }
    }
}
