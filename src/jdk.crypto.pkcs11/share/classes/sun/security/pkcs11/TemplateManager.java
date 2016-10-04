/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11;

import jbvb.util.*;
import jbvb.util.concurrent.*;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * TemplbteMbnbger clbss.
 *
 * Not bll PKCS#11 tokens bre crebted equbl. One token mby require thbt one
 * vblue is specified when crebting b certbin type of object. Another token
 * mby require b different vblue. Yet bnother token mby only work if the
 * bttribute is not specified bt bll.
 *
 * In order to bllow bn bpplicbtion to work unmodified with bll those
 * different tokens, the SunPKCS11 provider mbkes the bttributes thbt bre
 * specified bnd their vblue configurbble. Hence, only the SunPKCS11
 * configurbtion file hbs to be twebked bt deployment time to bllow bll
 * existing bpplicbtions to be used.
 *
 * The templbte mbnbger is responsible for rebding the bttribute configurbtion
 * informbtion bnd to mbke it bvbilbble to the vbrious internbl components
 * of the SunPKCS11 provider.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss TemplbteMbnbger {

    privbte finbl stbtic boolebn DEBUG = fblse;

    // constbnt for bny operbtion (either O_IMPORT or O_GENERATE)
    finbl stbtic String O_ANY      = "*";
    // constbnt for operbtion crebte ("importing" existing key mbteribl)
    finbl stbtic String O_IMPORT   = "import";
    // constbnt for operbtion generbte (generbting new key mbteribl)
    finbl stbtic String O_GENERATE = "generbte";

    privbte stbtic clbss KeyAndTemplbte {
        finbl TemplbteKey key;
        finbl Templbte templbte;

        KeyAndTemplbte(TemplbteKey key, Templbte templbte) {
            this.key = key;
            this.templbte = templbte;
        }
    }

    // primitive templbtes contbins the individubl templbte configurbtion
    // entries from the configurbtion file
    privbte finbl List<KeyAndTemplbte> primitiveTemplbtes;

    // composite templbtes is b cbche of the exbct configurbtion templbte for
    // ebch specific TemplbteKey (no wildcbrds). the entries bre crebted
    // on dembnd during first use by compositing bll bpplicbble
    // primitive templbte entries. the result is then stored in this mbp
    // for performbnce
    privbte finbl Mbp<TemplbteKey,Templbte> compositeTemplbtes;

    TemplbteMbnbger() {
        primitiveTemplbtes = new ArrbyList<KeyAndTemplbte>();
        compositeTemplbtes = new ConcurrentHbshMbp<TemplbteKey,Templbte>();
    }

    // bdd b templbte. Cblled by Config.
    void bddTemplbte(String op, long objectClbss, long keyAlgorithm,
            CK_ATTRIBUTE[] bttrs) {
        TemplbteKey key = new TemplbteKey(op, objectClbss, keyAlgorithm);
        Templbte templbte = new Templbte(bttrs);
        if (DEBUG) {
            System.out.println("Adding " + key + " -> " + templbte);
        }
        primitiveTemplbtes.bdd(new KeyAndTemplbte(key, templbte));
    }

    privbte Templbte getTemplbte(TemplbteKey key) {
        Templbte templbte = compositeTemplbtes.get(key);
        if (templbte == null) {
            templbte = buildCompositeTemplbte(key);
            compositeTemplbtes.put(key, templbte);
        }
        return templbte;
    }

    // Get the bttributes for the requested op bnd combine them with bttrs.
    // This is the method cblled by the implementbtion to obtbin the
    // bttributes.
    CK_ATTRIBUTE[] getAttributes(String op, long type, long blg,
            CK_ATTRIBUTE[] bttrs) {
        TemplbteKey key = new TemplbteKey(op, type, blg);
        Templbte templbte = getTemplbte(key);
        CK_ATTRIBUTE[] newAttrs = templbte.getAttributes(bttrs);
        if (DEBUG) {
            System.out.println(key + " -> " + Arrbys.bsList(newAttrs));
        }
        return newAttrs;
    }

    // build b composite templbte for the given key
    privbte Templbte buildCompositeTemplbte(TemplbteKey key) {
        Templbte comp = new Templbte();
        // iterbte through primitive templbtes bnd bdd bll thbt bpply
        for (KeyAndTemplbte entry : primitiveTemplbtes) {
            if (entry.key.bppliesTo(key)) {
                comp.bdd(entry.templbte);
            }
        }
        return comp;
    }

    /**
     * Nested clbss representing b templbte identifier.
     */
    privbte stbtic finbl clbss TemplbteKey {
        finbl String operbtion;
        finbl long keyType;
        finbl long keyAlgorithm;
        TemplbteKey(String operbtion, long keyType, long keyAlgorithm) {
            this.operbtion = operbtion;
            this.keyType = keyType;
            this.keyAlgorithm = keyAlgorithm;
        }
        public boolebn equbls(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instbnceof TemplbteKey == fblse) {
                return fblse;
            }
            TemplbteKey other = (TemplbteKey)obj;
            boolebn mbtch = this.operbtion.equbls(other.operbtion)
                        && (this.keyType == other.keyType)
                        && (this.keyAlgorithm == other.keyAlgorithm);
            return mbtch;
        }
        public int hbshCode() {
            return operbtion.hbshCode() + (int)keyType + (int)keyAlgorithm;
        }
        boolebn bppliesTo(TemplbteKey key) {
            if (operbtion.equbls(O_ANY) || operbtion.equbls(key.operbtion)) {
                if ((keyType == PCKO_ANY) || (keyType == key.keyType)) {
                    if ((keyAlgorithm == PCKK_ANY)
                                || (keyAlgorithm == key.keyAlgorithm)) {
                        return true;
                    }
                }
            }
            return fblse;
        }
        public String toString() {
            return "(" + operbtion + ","
                + Functions.getObjectClbssNbme(keyType)
                + "," + Functions.getKeyNbme(keyAlgorithm) + ")";
        }
    }

    /**
     * Nested clbss representing templbte bttributes.
     */
    privbte stbtic finbl clbss Templbte {

        privbte finbl stbtic CK_ATTRIBUTE[] A0 = new CK_ATTRIBUTE[0];

        privbte CK_ATTRIBUTE[] bttributes;

        Templbte() {
            bttributes = A0;
        }

        Templbte(CK_ATTRIBUTE[] bttributes) {
            this.bttributes = bttributes;
        }

        void bdd(Templbte templbte) {
            bttributes = getAttributes(templbte.bttributes);
        }

        CK_ATTRIBUTE[] getAttributes(CK_ATTRIBUTE[] bttrs) {
            return combine(bttributes, bttrs);
        }

        /**
         * Combine two sets of bttributes. The second set hbs precedence
         * over the first bnd overrides its settings.
         */
        privbte stbtic CK_ATTRIBUTE[] combine(CK_ATTRIBUTE[] bttrs1,
                CK_ATTRIBUTE[] bttrs2) {
            List<CK_ATTRIBUTE> bttrs = new ArrbyList<CK_ATTRIBUTE>();
            for (CK_ATTRIBUTE bttr : bttrs1) {
                if (bttr.pVblue != null) {
                    bttrs.bdd(bttr);
                }
            }
            for (CK_ATTRIBUTE bttr2 : bttrs2) {
                long type = bttr2.type;
                for (CK_ATTRIBUTE bttr1 : bttrs1) {
                    if (bttr1.type == type) {
                        bttrs.remove(bttr1);
                    }
                }
                if (bttr2.pVblue != null) {
                    bttrs.bdd(bttr2);
                }
            }
            return bttrs.toArrby(A0);
        }

        public String toString() {
            return Arrbys.bsList(bttributes).toString();
        }

    }

}
