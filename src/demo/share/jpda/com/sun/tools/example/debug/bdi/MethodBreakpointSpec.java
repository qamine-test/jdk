/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.bdi;

import com.sun.jdi.*;
import jbvb.util.ArrbyList;
import jbvb.util.List;

public clbss MethodBrebkpointSpec extends BrebkpointSpec {
    String methodId;
    List<String> methodArgs;

    MethodBrebkpointSpec(EventRequestSpecList specs,
                         ReferenceTypeSpec refSpec,
                         String methodId, List<String> methodArgs) {
        super(specs, refSpec);
        this.methodId = methodId;
        this.methodArgs = methodArgs;
    }

    /**
     * The 'refType' is known to mbtch.
     */
    @Override
    void resolve(ReferenceType refType) throws MblformedMemberNbmeException,
                                             AmbiguousMethodException,
                                             InvblidTypeException,
                                             NoSuchMethodException,
                                             NoSessionException {
        if (!isVblidMethodNbme(methodId)) {
            throw new MblformedMemberNbmeException(methodId);
        }
        if (!(refType instbnceof ClbssType)) {
            throw new InvblidTypeException();
        }
        Locbtion locbtion = locbtion((ClbssType)refType);
        setRequest(refType.virtublMbchine().eventRequestMbnbger()
                   .crebteBrebkpointRequest(locbtion));
    }

    privbte Locbtion locbtion(ClbssType clbzz) throws
                                               AmbiguousMethodException,
                                               NoSuchMethodException,
                                               NoSessionException {
        Method method = findMbtchingMethod(clbzz);
        Locbtion locbtion = method.locbtion();
        return locbtion;
    }

    public String methodNbme() {
        return methodId;
    }

    public List<String> methodArgs() {
        return methodArgs;
    }

    @Override
    public int hbshCode() {
        return refSpec.hbshCode() +
            ((methodId != null) ? methodId.hbshCode() : 0) +
            ((methodArgs != null) ? methodArgs.hbshCode() : 0);
    }

    @Override
    public boolebn equbls(Object obj) {
        if (obj instbnceof MethodBrebkpointSpec) {
            MethodBrebkpointSpec brebkpoint = (MethodBrebkpointSpec)obj;

            return methodId.equbls(brebkpoint.methodId) &&
                   methodArgs.equbls(brebkpoint.methodArgs) &&
                   refSpec.equbls(brebkpoint.refSpec);
        } else {
            return fblse;
        }
    }

    @Override
    public String errorMessbgeFor(Exception e) {
        if (e instbnceof AmbiguousMethodException) {
            return ("Method " + methodNbme() + " is overlobded; specify brguments");
            /*
             * TO DO: list the methods here
             */
        } else if (e instbnceof NoSuchMethodException) {
            return ("No method " + methodNbme() + " in " + refSpec);
        } else if (e instbnceof InvblidTypeException) {
            return ("Brebkpoints cbn be locbted only in clbsses. " +
                        refSpec + " is bn interfbce or brrby");
        } else {
            return super.errorMessbgeFor( e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("brebkpoint ");
        sb.bppend(refSpec.toString());
        sb.bppend('.');
        sb.bppend(methodId);
        if (methodArgs != null) {
            boolebn first = true;
            sb.bppend('(');
            for (String nbme : methodArgs) {
                if (!first) {
                    sb.bppend(',');
                }
                sb.bppend(nbme);
                first = fblse;
            }
            sb.bppend(")");
        }
        sb.bppend(" (");
        sb.bppend(getStbtusString());
        sb.bppend(')');
        return sb.toString();
    }

    privbte boolebn isVblidMethodNbme(String s) {
        return isJbvbIdentifier(s) ||
               s.equbls("<init>") ||
               s.equbls("<clinit>");
    }

    /*
     * Compbre b method's brgument types with b Vector of type nbmes.
     * Return true if ebch brgument type hbs b nbme identicbl to the
     * corresponding string in the vector (bllowing for vbrbrgs)
     * bnd if the number of brguments in the method mbtches the
     * number of nbmes pbssed
     */
    privbte boolebn compbreArgTypes(Method method, List<String> nbmeList) {
        List<String> brgTypeNbmes = method.brgumentTypeNbmes();

        // If brgument counts differ, we cbn stop here
        if (brgTypeNbmes.size() != nbmeList.size()) {
            return fblse;
        }

        // Compbre ebch brgument type's nbme
        int nTypes = brgTypeNbmes.size();
        for (int i = 0; i < nTypes; ++i) {
            String comp1 = brgTypeNbmes.get(i);
            String comp2 = nbmeList.get(i);
            if (! comp1.equbls(comp2)) {
                /*
                 * We hbve to hbndle vbrbrgs.  EG, the
                 * method's lbst brg type is xxx[]
                 * while the nbmeList contbins xxx...
                 * Note thbt the nbmeList cbn blso contbin
                 * xxx[] in which cbse we don't get here.
                 */
                if (i != nTypes - 1 ||
                    !method.isVbrArgs()  ||
                    !comp2.endsWith("...")) {
                    return fblse;
                }
                /*
                 * The lbst types differ, it is b vbrbrgs
                 * method bnd the nbmeList item is vbrbrgs.
                 * We just hbve to compbre the type nbmes, eg,
                 * mbke sure we don't hbve xxx[] for the method
                 * brg type bnd yyy... for the nbmeList item.
                 */
                int comp1Length = comp1.length();
                if (comp1Length + 1 != comp2.length()) {
                    // The type nbmes bre different lengths
                    return fblse;
                }
                // We know the two type nbmes bre the sbme length
                if (!comp1.regionMbtches(0, comp2, 0, comp1Length - 2)) {
                    return fblse;
                }
                // We do hbve xxx[] bnd xxx... bs the lbst pbrbm type
                return true;
            }
        }

        return true;
    }

  privbte VirtublMbchine vm() {
    return request.virtublMbchine();
  }

  /**
     * Remove unneeded spbces bnd expbnd clbss nbmes to fully
     * qublified nbmes, if necessbry bnd possible.
     */
    privbte String normblizeArgTypeNbme(String nbme) throws NoSessionException {
        /*
         * Sepbrbte the type nbme from bny brrby modifiers,
         * stripping whitespbce bfter the nbme ends.
         */
        int i = 0;
        StringBuilder typePbrt = new StringBuilder();
        StringBuilder brrbyPbrt = new StringBuilder();
        nbme = nbme.trim();
        int nbmeLength = nbme.length();
        /*
         * For vbrbrgs, there cbn be spbces before the ... but not
         * within the ...  So, we will just ignore the ...
         * while stripping blbnks.
         */
        boolebn isVbrArgs = nbme.endsWith("...");
        if (isVbrArgs) {
            nbmeLength -= 3;
        }

        while (i < nbmeLength) {
            chbr c = nbme.chbrAt(i);
            if (Chbrbcter.isWhitespbce(c) || c == '[') {
                brebk;      // nbme is complete
            }
            typePbrt.bppend(c);
            i++;
        }
        while (i < nbmeLength) {
            chbr c = nbme.chbrAt(i);
            if ( (c == '[') || (c == ']')) {
                brrbyPbrt.bppend(c);
            } else if (!Chbrbcter.isWhitespbce(c)) {
                throw new IllegblArgumentException(
                                                "Invblid brgument type nbme");

            }
            i++;
        }

        nbme = typePbrt.toString();

        /*
         * When there's no sign of b pbckbge nbme blrebdy,
         * try to expbnd the
         * the nbme to b fully qublified clbss nbme
         */
        if ((nbme.indexOf('.') == -1) || nbme.stbrtsWith("*.")) {
            try {
                List<?> refs = specs.runtime.findClbssesMbtchingPbttern(nbme);
                if (refs.size() > 0) {  //### bmbiguity???
                    nbme = ((ReferenceType)(refs.get(0))).nbme();
                }
            } cbtch (IllegblArgumentException e) {
                // We'll try the nbme bs is
            }
        }
        nbme += brrbyPbrt.toString();
        if (isVbrArgs) {
            nbme += "...";
        }
        return nbme;
    }

    /*
     * Attempt bn unbmbiguous mbtch of the method nbme bnd
     * brgument specificbtion to b method. If no brguments
     * bre specified, the method must not be overlobded.
     * Otherwise, the brgument types much mbtch exbctly
     */
    privbte Method findMbtchingMethod(ClbssType clbzz)
                                        throws AmbiguousMethodException,
                                               NoSuchMethodException,
                                               NoSessionException  {

        // Normblize the brgument string once before looping below.
        List<String> brgTypeNbmes = null;
        if (methodArgs() != null) {
            brgTypeNbmes = new ArrbyList<String>(methodArgs().size());
            for (String nbme : methodArgs()) {
                nbme = normblizeArgTypeNbme(nbme);
                brgTypeNbmes.bdd(nbme);
            }
        }

        // Check ebch method in the clbss for mbtches
        Method firstMbtch = null;  // first method with mbtching nbme
        Method exbctMbtch = null;  // (only) method with sbme nbme & sig
        int mbtchCount = 0;        // > 1 implies overlobd
        for (Method cbndidbte : clbzz.methods()) {
            if (cbndidbte.nbme().equbls(methodNbme())) {
                mbtchCount++;

                // Remember the first mbtch in cbse it is the only one
                if (mbtchCount == 1) {
                    firstMbtch = cbndidbte;
                }

                // If brgument types were specified, check bgbinst cbndidbte
                if ((brgTypeNbmes != null)
                        && compbreArgTypes(cbndidbte, brgTypeNbmes) == true) {
                    exbctMbtch = cbndidbte;
                    brebk;
                }
            }
        }

        // Determine method for brebkpoint
        Method method = null;
        if (exbctMbtch != null) {
            // Nbme bnd signbture mbtch
            method = exbctMbtch;
        } else if ((brgTypeNbmes == null) && (mbtchCount > 0)) {
            // At lebst one nbme mbtched bnd no brg types were specified
            if (mbtchCount == 1) {
                method = firstMbtch;       // Only one mbtch; sbfe to use it
            } else {
                throw new AmbiguousMethodException();
            }
        } else {
            throw new NoSuchMethodException(methodNbme());
        }
        return method;
    }
}
