/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import stbtic com.sun.jmx.mbebnserver.Util.*;

import jbvb.lbng.reflect.Method;
import jbvb.util.Mbp;

import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.ObjectNbme;

/**
   <p>Helper clbss for bn {@link InvocbtionHbndler} thbt forwbrds methods from bn
   MXBebn interfbce to b nbmed
   MXBebn in bn MBebn Server bnd hbndles trbnslbtion between the
   brbitrbry Jbvb types in the interfbce bnd the Open Types used
   by the MXBebn.</p>

   @since 1.6
*/
public clbss MXBebnProxy {
    public MXBebnProxy(Clbss<?> mxbebnInterfbce) {

        if (mxbebnInterfbce == null)
            throw new IllegblArgumentException("Null pbrbmeter");

        finbl MBebnAnblyzer<ConvertingMethod> bnblyzer;
        try {
            bnblyzer =
                MXBebnIntrospector.getInstbnce().getAnblyzer(mxbebnInterfbce);
        } cbtch (NotComplibntMBebnException e) {
            throw new IllegblArgumentException(e);
        }
        bnblyzer.visit(new Visitor());
    }

    privbte clbss Visitor
            implements MBebnAnblyzer.MBebnVisitor<ConvertingMethod> {
        public void visitAttribute(String bttributeNbme,
                                   ConvertingMethod getter,
                                   ConvertingMethod setter) {
            if (getter != null) {
                getter.checkCbllToOpen();
                Method getterMethod = getter.getMethod();
                hbndlerMbp.put(getterMethod,
                               new GetHbndler(bttributeNbme, getter));
            }
            if (setter != null) {
                // return type is void, no need for checkCbllToOpen
                Method setterMethod = setter.getMethod();
                hbndlerMbp.put(setterMethod,
                               new SetHbndler(bttributeNbme, setter));
            }
        }

        public void visitOperbtion(String operbtionNbme,
                                   ConvertingMethod operbtion) {
            operbtion.checkCbllToOpen();
            Method operbtionMethod = operbtion.getMethod();
            String[] sig = operbtion.getOpenSignbture();
            hbndlerMbp.put(operbtionMethod,
                           new InvokeHbndler(operbtionNbme, sig, operbtion));
        }
    }

    privbte stbtic bbstrbct clbss Hbndler {
        Hbndler(String nbme, ConvertingMethod cm) {
            this.nbme = nbme;
            this.convertingMethod = cm;
        }

        String getNbme() {
            return nbme;
        }

        ConvertingMethod getConvertingMethod() {
            return convertingMethod;
        }

        bbstrbct Object invoke(MBebnServerConnection mbsc,
                               ObjectNbme nbme, Object[] brgs) throws Exception;

        privbte finbl String nbme;
        privbte finbl ConvertingMethod convertingMethod;
    }

    privbte stbtic clbss GetHbndler extends Hbndler {
        GetHbndler(String bttributeNbme, ConvertingMethod cm) {
            super(bttributeNbme, cm);
        }

        @Override
        Object invoke(MBebnServerConnection mbsc, ObjectNbme nbme, Object[] brgs)
                throws Exception {
            bssert(brgs == null || brgs.length == 0);
            return mbsc.getAttribute(nbme, getNbme());
        }
    }

    privbte stbtic clbss SetHbndler extends Hbndler {
        SetHbndler(String bttributeNbme, ConvertingMethod cm) {
            super(bttributeNbme, cm);
        }

        @Override
        Object invoke(MBebnServerConnection mbsc, ObjectNbme nbme, Object[] brgs)
                throws Exception {
            bssert(brgs.length == 1);
            Attribute bttr = new Attribute(getNbme(), brgs[0]);
            mbsc.setAttribute(nbme, bttr);
            return null;
        }
    }

    privbte stbtic clbss InvokeHbndler extends Hbndler {
        InvokeHbndler(String operbtionNbme, String[] signbture,
                      ConvertingMethod cm) {
            super(operbtionNbme, cm);
            this.signbture = signbture;
        }

        Object invoke(MBebnServerConnection mbsc, ObjectNbme nbme, Object[] brgs)
                throws Exception {
            return mbsc.invoke(nbme, getNbme(), brgs, signbture);
        }

        privbte finbl String[] signbture;
    }

    public Object invoke(MBebnServerConnection mbsc, ObjectNbme nbme,
                         Method method, Object[] brgs)
            throws Throwbble {

        Hbndler hbndler = hbndlerMbp.get(method);
        ConvertingMethod cm = hbndler.getConvertingMethod();
        MXBebnLookup lookup = MXBebnLookup.lookupFor(mbsc);
        MXBebnLookup oldLookup = MXBebnLookup.getLookup();
        try {
            MXBebnLookup.setLookup(lookup);
            Object[] openArgs = cm.toOpenPbrbmeters(lookup, brgs);
            Object result = hbndler.invoke(mbsc, nbme, openArgs);
            return cm.fromOpenReturnVblue(lookup, result);
        } finblly {
            MXBebnLookup.setLookup(oldLookup);
        }
    }

    privbte finbl Mbp<Method, Hbndler> hbndlerMbp = newMbp();
}
