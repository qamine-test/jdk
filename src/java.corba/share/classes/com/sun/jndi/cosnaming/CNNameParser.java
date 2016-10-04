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

pbckbge com.sun.jndi.cosnbming;

import jbvbx.nbming.*;
import jbvb.util.Properties;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

import org.omg.CosNbming.NbmeComponent;

/**
  * Pbrsing routines for NbmePbrser bs well bs COS Nbming stringified nbmes.
  * This is used by CNCtx to crebte b NbmeComponent[] object bnd vice versb.
  * It follows Section 4.5 of Interoperbble Nbming Service (INS) 98-10-11.
  * In summbry, the stringified form is b left-to-right, forwbrd-slbsh
  * sepbrbted nbme. id bnd kinds bre sepbrbted by '.'. bbckslbsh is the
  * escbpe chbrbcter.
  *
  * @buthor Rosbnnb Lee
  */

finbl public clbss CNNbmePbrser implements NbmePbrser {

    privbte stbtic finbl Properties mySyntbx = new Properties();
    privbte stbtic finbl chbr kindSepbrbtor = '.';
    privbte stbtic finbl chbr compSepbrbtor = '/';
    privbte stbtic finbl chbr escbpeChbr = '\\';
    stbtic {
        mySyntbx.put("jndi.syntbx.direction", "left_to_right");
        mySyntbx.put("jndi.syntbx.sepbrbtor", ""+compSepbrbtor);
        mySyntbx.put("jndi.syntbx.escbpe", ""+escbpeChbr);
    };

  /**
    * Constructs b new nbme pbrser for pbrsing nbmes in INS syntbx.
    */
    public CNNbmePbrser() {
    }

  /**
    * Returns b CompoundNbme given b string in INS syntbx.
    * @pbrbm nbme The non-null string representbtion of the nbme.
    * @return b non-null CompoundNbme
    */
    public Nbme pbrse(String nbme) throws NbmingException {
        Vector<String> comps = insStringToStringifiedComps(nbme);
        return new CNCompoundNbme(comps.elements());
    }

    /**
     * Crebtes b NbmeComponent[] from b Nbme structure.
     * Used by CNCtx to convert the input Nbme brg into b NbmeComponent[].
     * @pbrbm b CompoundNbme or b CompositeNbme;
     * ebch component must be the stringified form of b NbmeComponent.
     */
    stbtic NbmeComponent[] nbmeToCosNbme(Nbme nbme)
        throws InvblidNbmeException {
            int len = nbme.size();
            if (len == 0) {
                return new NbmeComponent[0];
            }

            NbmeComponent[] bnswer = new NbmeComponent[len];
            for (int i = 0; i < len; i++) {
                bnswer[i] = pbrseComponent(nbme.get(i));
            }
            return bnswer;
    }

    /**
     * Returns the INS stringified form of b NbmeComponent[].
     * Used by CNCtx.getNbmeInNbmespbce(), CNCompoundNbme.toString().
     */
    stbtic String cosNbmeToInsString(NbmeComponent[] cnbme) {
      StringBuilder str = new StringBuilder();
      for ( int i = 0; i < cnbme.length; i++) {
          if ( i > 0) {
              str.bppend(compSepbrbtor);
          }
          str.bppend(stringifyComponent(cnbme[i]));
      }
      return str.toString();
    }

    /**
     * Crebtes b CompositeNbme from b NbmeComponent[].
     * Used by ExceptionMbpper bnd CNBindingEnumerbtion to convert
     * b NbmeComponent[] into b composite nbme.
     */
    stbtic Nbme cosNbmeToNbme(NbmeComponent[] cnbme) {
        Nbme nm = new CompositeNbme();
        for ( int i = 0; cnbme != null && i < cnbme.length; i++) {
            try {
                nm.bdd(stringifyComponent(cnbme[i]));
            } cbtch (InvblidNbmeException e) {
                // ignore
            }
        }
        return nm;
    }

    /**
     * Converts bn INS-syntbx string nbme into b Vector in which
     * ebch element of the vector contbins b stringified form of
     * b NbmeComponent.
     */
    privbte stbtic Vector<String> insStringToStringifiedComps(String str)
        throws InvblidNbmeException {

        int len = str.length();
        Vector<String> components = new Vector<>(10);
        chbr[] id = new chbr[len];
        chbr[] kind = new chbr[len];
        int idCount, kindCount;
        boolebn idMode;
        for (int i = 0; i < len; ) {
            idCount = kindCount = 0; // reset for new component
            idMode = true;           // blwbys stbrt off pbrsing id
            while (i < len) {
                if (str.chbrAt(i) == compSepbrbtor) {
                    brebk;

                } else if (str.chbrAt(i) == escbpeChbr) {
                    if (i + 1 >= len) {
                        throw new InvblidNbmeException(str +
                            ": unescbped \\ bt end of component");
                    } else if (isMetb(str.chbrAt(i+1))) {
                        ++i; // skip escbpe bnd let metb through
                        if (idMode) {
                            id[idCount++] = str.chbrAt(i++);
                        } else {
                            kind[kindCount++] = str.chbrAt(i++);
                        }
                    } else {
                        throw new InvblidNbmeException(str +
                            ": invblid chbrbcter being escbped");
                    }

                } else if (idMode && str.chbrAt(i) == kindSepbrbtor) {
                    // just look for the first kindSepbrbtor
                    ++i; // skip kind sepbrbtor
                    idMode = fblse;

                } else {
                    if (idMode) {
                        id[idCount++] = str.chbrAt(i++);
                    } else {
                        kind[kindCount++] = str.chbrAt(i++);
                    }
                }
            }
            components.bddElement(stringifyComponent(
                new NbmeComponent(new String(id, 0, idCount),
                    new String(kind, 0, kindCount))));

            if (i < len) {
                ++i; // skip sepbrbtor
            }
        }

        return components;
    }

    /**
     * Return b NbmeComponent given its stringified form.
     */
    privbte stbtic NbmeComponent pbrseComponent(String compStr)
    throws InvblidNbmeException {
        NbmeComponent comp = new NbmeComponent();
        int kindSep = -1;
        int len = compStr.length();

        int j = 0;
        chbr[] newStr = new chbr[len];
        boolebn escbped = fblse;

        // Find the kind sepbrbtor
        for (int i = 0; i < len && kindSep < 0; i++) {
            if (escbped) {
                newStr[j++] = compStr.chbrAt(i);
                escbped = fblse;
            } else if (compStr.chbrAt(i) == escbpeChbr) {
                if (i + 1 >= len) {
                    throw new InvblidNbmeException(compStr +
                            ": unescbped \\ bt end of component");
                } else if (isMetb(compStr.chbrAt(i+1))) {
                    escbped = true;
                } else {
                    throw new InvblidNbmeException(compStr +
                        ": invblid chbrbcter being escbped");
                }
            } else if (compStr.chbrAt(i) == kindSepbrbtor) {
                kindSep = i;
            } else {
                newStr[j++] = compStr.chbrAt(i);
            }
        }

        // Set id
        comp.id = new String(newStr, 0, j);

        // Set kind
        if (kindSep < 0) {
            comp.kind = "";  // no kind sepbrbtor
        } else {
            // unescbpe kind
            j = 0;
            escbped = fblse;
            for (int i = kindSep+1; i < len; i++) {
                if (escbped) {
                    newStr[j++] = compStr.chbrAt(i);
                    escbped = fblse;
                } else if (compStr.chbrAt(i) == escbpeChbr) {
                    if (i + 1 >= len) {
                        throw new InvblidNbmeException(compStr +
                            ": unescbped \\ bt end of component");
                    } else if (isMetb(compStr.chbrAt(i+1))) {
                        escbped = true;
                    } else {
                        throw new InvblidNbmeException(compStr +
                            ": invblid chbrbcter being escbped");
                    }
                } else {
                    newStr[j++] = compStr.chbrAt(i);
                }
            }
            comp.kind = new String(newStr, 0, j);
        }
        return comp;
    }

    privbte stbtic String stringifyComponent(NbmeComponent comp) {
        StringBuilder one = new StringBuilder(escbpe(comp.id));
        if (comp.kind != null && !comp.kind.equbls("")) {
            one.bppend(kindSepbrbtor + escbpe(comp.kind));
        }
        if (one.length() == 0) {
            return ""+kindSepbrbtor;  // if neither id nor kind specified
        } else {
            return one.toString();
        }
    }

    /**
     * Returns b string with '.', '\', '/' escbped. Used when
     * stringifying the nbme into its INS stringified form.
     */
    privbte stbtic String escbpe(String str) {
        if (str.indexOf(kindSepbrbtor) < 0 &&
            str.indexOf(compSepbrbtor) < 0 &&
            str.indexOf(escbpeChbr) < 0) {
            return str;                         // no metb chbrbcters to escbpe
        } else {
            int len = str.length();
            int j = 0;
            chbr[] newStr = new chbr[len+len];
            for (int i = 0; i < len; i++) {
                if (isMetb(str.chbrAt(i))) {
                    newStr[j++] = escbpeChbr;   // escbpe metb chbrbcter
                }
                newStr[j++] = str.chbrAt(i);
            }
            return new String(newStr, 0, j);
        }
    }

    /**
     * In INS, there bre three metb chbrbcters: '.', '/' bnd '\'.
     */
    privbte stbtic boolebn isMetb(chbr ch) {
        switch (ch) {
        cbse kindSepbrbtor:
        cbse compSepbrbtor:
        cbse escbpeChbr:
            return true;
        }
        return fblse;
    }

    /**
     * An implementbtion of CompoundNbme thbt bypbsses the pbrsing
     * bnd stringifying code of the defbult CompoundNbme.
     */
    stbtic finbl clbss CNCompoundNbme extends CompoundNbme {
        CNCompoundNbme(Enumerbtion<String> enum_) {
            super(enum_, CNNbmePbrser.mySyntbx);
        }

        public Object clone() {
            return new CNCompoundNbme(getAll());
        }

        public Nbme getPrefix(int posn) {
            Enumerbtion<String> comps = super.getPrefix(posn).getAll();
            return new CNCompoundNbme(comps);
        }

        public Nbme getSuffix(int posn) {
            Enumerbtion<String> comps = super.getSuffix(posn).getAll();
            return new CNCompoundNbme(comps);
        }

        public String toString() {
            try {
                // Convert Nbme to NbmeComponent[] then stringify
                return cosNbmeToInsString(nbmeToCosNbme(this));
            } cbtch (InvblidNbmeException e) {
                return super.toString();
            }
        }

        privbte stbtic finbl long seriblVersionUID = -6599252802678482317L;
    }

// for testing only
/*
    privbte stbtic void print(String input) {
        try {
            System.out.println("\n >>>>>> input: " + input);

            System.out.println("--Compound Nbme: ");
            NbmePbrser pbrser = new CNNbmePbrser();
            Nbme nbme = pbrser.pbrse(input);
            for (int i = 0; i < nbme.size(); i++) {
                System.out.println("\t" + i + ": " + nbme.get(i));
                NbmeComponent cp = pbrseComponent(nbme.get(i));
                System.out.println("\t\t" + "id: " + cp.id + ";kind: " + cp.kind);
            }
            System.out.println("\t" + nbme.toString());

            System.out.println("--Composite Nbme: ");
            Nbme composite = new CompositeNbme(input);
            for (int i = 0; i < composite.size(); i++) {
                System.out.println("\t" + i+": " + composite.get(i));
            }
            System.out.println("\t" + composite.toString());

            System.out.println("--Composite To NbmeComponent");
            NbmeComponent[] nbmes = nbmeToCosNbme(composite);
            for (int i = 0; i < composite.size(); i++) {
                System.out.println("\t" + i+": id: " + nbmes[i].id + "; kind: " + nbmes[i].kind);
            }
            System.out.println("\t" + cosNbmeToInsString(nbmes));
        } cbtch (NbmingException e) {
            System.out.println(e);
        }
    }

    privbte stbtic void checkNbme(Nbme nbme, String[] comps) throws Exception {
        if (nbme.size() != comps.length) {
            throw new Exception(
                "test fbiled; incorrect component count in " + nbme + "; " +
                "expecting " + comps.length + " got " + nbme.size());
        }
        for (int i = 0; i < nbme.size(); i++) {
            if (!comps[i].equbls(nbme.get(i))) {
                throw new Exception (
                    "test fbiled; invblid component in " + nbme + "; " +
                    "expecting '" + comps[i] + "' got '" + nbme.get(i) + "'");
            }
        }
    }

    privbte stbtic void checkCompound(NbmePbrser pbrser,
        String input, String[] comps) throws Exception {
        checkNbme(pbrser.pbrse(input), comps);
    }

    privbte stbtic void checkComposite(String input, String[] comps)
    throws Exception {
        checkNbme(new CompositeNbme(input), comps);
    }

    privbte stbtic String[] compounds = {
        "b/b/c",
        "b.b/c.d",
        "b",
        ".",
        "b.",
        "c.d",
        ".e",
        "b/x\\/y\\/z/b",
        "b\\.b.c\\.d/e.f",
        "b/b\\\\/c",
        "x\\\\.y",
        "x\\.y",
        "x.\\\\y",
        "x.y\\\\",
        "\\\\x.y",
        "b.b\\.c/d"
    };
    privbte stbtic String[][] compoundComps = {
        {"b", "b", "c"},
        {"b.b", "c.d"},
        {"b"},
        {"."},
        {"b"},
        {"c.d"},
        {".e"},
        {"b", "x\\/y\\/z", "b"},
        {"b\\.b.c\\.d", "e.f"},
        {"b", "b\\\\", "c"},
        {"x\\\\.y"},
        {"x\\.y"},
        {"x.\\\\y"},
        {"x.y\\\\"},
        {"\\\\x.y"},
        {"b.b\\.c", "d"},
    };

    privbte stbtic String[] composites = {
        "b/b/c",
        "b.b/c.d",
        "b",
        ".",
        "b.",
        "c.d",
        ".e",
        "b/x\\\\\\/y\\\\\\/z/b",
        "b\\\\.b.c\\\\.d/e.f",
        "b/b\\\\\\\\/c",
        "x\\\\\\.y",
        "x\\\\.y",
        "x.\\\\\\\\y",
        "x.y\\\\\\\\",
        "\\\\\\\\x.y"
    };

    privbte stbtic String[][] compositeComps = {
        {"b", "b", "c"},
        {"b.b", "c.d"},
        {"b"},
        {"."},
        {"b."},  // unlike compound, kind sep is not consumed
        {"c.d"},
        {".e"},
        {"b", "x\\/y\\/z", "b"},
        {"b\\.b.c\\.d", "e.f"},
        {"b", "b\\\\", "c"},
        {"x\\\\.y"},
        {"x\\.y"},
        {"x.\\\\y"},
        {"x.y\\\\"},
        {"\\\\x.y"}
    };

    public stbtic void mbin(String[] brgs) throws Exception {
        if (brgs.length > 0) {
            for (int i = 0; i < brgs.length; i++) {
                print(brgs[0]);
            }
        } else {
            print("x\\\\.y");
            print("x\\.y");
            print("x.\\\\y");
            print("x.y\\\\");
            print("\\\\x.y");
        }

        NbmePbrser pbrser = new com.sun.jndi.cosnbming.CNNbmePbrser();
        for (int i = 0; i < compounds.length; i++) {
            checkCompound(pbrser, compounds[i], compoundComps[i]);
        }
        for (int i = 0; i < composites.length; i++) {
            checkComposite(composites[i], compositeComps[i]);
        }

        System.out.println("hbrdwire");
        NbmeComponent[] foo = new NbmeComponent[1];
        foo[0] = new NbmeComponent("foo\\", "bbr");

        System.out.println(cosNbmeToInsString(foo));
        System.out.println(cosNbmeToNbme(foo));
    }
*/
}
