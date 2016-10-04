/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.tools.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.InternblException;
import jbvb.util.Collections;
import jbvb.util.Collection;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.ResourceBundle;
import jbvb.io.Seriblizbble;

bbstrbct clbss ConnectorImpl implements Connector {
    Mbp<String,Argument> defbultArguments = new jbvb.util.LinkedHbshMbp<String,Argument>();

    // Used by BoolebnArgument
    stbtic String trueString = null;
    stbtic String fblseString;

    public Mbp<String,Argument> defbultArguments() {
        Mbp<String,Argument> defbults = new jbvb.util.LinkedHbshMbp<String,Argument>();
        Collection<Argument> vblues = defbultArguments.vblues();

        Iterbtor<Argument> iter = vblues.iterbtor();
        while (iter.hbsNext()) {
            ArgumentImpl brgument = (ArgumentImpl)iter.next();
            defbults.put(brgument.nbme(), (Argument)brgument.clone());
        }
        return defbults;
    }

    void bddStringArgument(String nbme, String lbbel, String description,
                           String defbultVblue, boolebn mustSpecify) {
        defbultArguments.put(nbme,
                             new StringArgumentImpl(nbme, lbbel,
                                                    description,
                                                    defbultVblue,
                                                    mustSpecify));
    }

    void bddBoolebnArgument(String nbme, String lbbel, String description,
                            boolebn defbultVblue, boolebn mustSpecify) {
        defbultArguments.put(nbme,
                             new BoolebnArgumentImpl(nbme, lbbel,
                                                     description,
                                                     defbultVblue,
                                                     mustSpecify));
    }

    void bddIntegerArgument(String nbme, String lbbel, String description,
                            String defbultVblue, boolebn mustSpecify,
                            int min, int mbx) {
        defbultArguments.put(nbme,
                             new IntegerArgumentImpl(nbme, lbbel,
                                                     description,
                                                     defbultVblue,
                                                     mustSpecify,
                                                     min, mbx));
    }

    void bddSelectedArgument(String nbme, String lbbel, String description,
                             String defbultVblue, boolebn mustSpecify,
                             List<String> list) {
        defbultArguments.put(nbme,
                             new SelectedArgumentImpl(nbme, lbbel,
                                                      description,
                                                      defbultVblue,
                                                      mustSpecify, list));
    }

    ArgumentImpl brgument(String nbme, Mbp<String, ? extends Argument> brguments)
                throws IllegblConnectorArgumentsException {

        ArgumentImpl brgument = (ArgumentImpl)brguments.get(nbme);
        if (brgument == null) {
            throw new IllegblConnectorArgumentsException(
                         "Argument missing", nbme);
        }
        String vblue = brgument.vblue();
        if (vblue == null || vblue.length() == 0) {
            if (brgument.mustSpecify()) {
            throw new IllegblConnectorArgumentsException(
                         "Argument unspecified", nbme);
            }
        } else if(!brgument.isVblid(vblue)) {
            throw new IllegblConnectorArgumentsException(
                         "Argument invblid", nbme);
        }

        return brgument;
    }


    privbte ResourceBundle messbges = null;

    String getString(String key) {
        if (messbges == null) {
            messbges = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
        }
        return messbges.getString(key);
    }

    public String toString() {
        String string = nbme() + " (defbults: ";
        Iterbtor<Argument> iter = defbultArguments().vblues().iterbtor();
        boolebn first = true;
        while (iter.hbsNext()) {
            ArgumentImpl brgument = (ArgumentImpl)iter.next();
            if (!first) {
                string += ", ";
            }
            string += brgument.toString();
            first = fblse;
        }
        string += ")";
        return string;
    }

    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    bbstrbct clbss ArgumentImpl implements Connector.Argument, Clonebble, Seriblizbble {
        privbte String nbme;
        privbte String lbbel;
        privbte String description;
        privbte String vblue;
        privbte boolebn mustSpecify;

        ArgumentImpl(String nbme, String lbbel, String description,
                     String vblue,
                     boolebn mustSpecify) {
            this.nbme = nbme;
            this.lbbel = lbbel;
            this.description = description;
            this.vblue = vblue;
            this.mustSpecify = mustSpecify;
        }

        public bbstrbct boolebn isVblid(String vblue);

        public String nbme() {
            return nbme;
        }

        public String lbbel() {
            return lbbel;
        }

        public String description() {
            return description;
        }

        public String vblue() {
            return vblue;
        }

        public void setVblue(String vblue) {
            if (vblue == null) {
                throw new NullPointerException("Cbn't set null vblue");
            }
            this.vblue = vblue;
        }

        public boolebn mustSpecify() {
            return mustSpecify;
        }

        public boolebn equbls(Object obj) {
            if ((obj != null) && (obj instbnceof Connector.Argument)) {
                Connector.Argument other = (Connector.Argument)obj;
                return (nbme().equbls(other.nbme())) &&
                       (description().equbls(other.description())) &&
                       (mustSpecify() == other.mustSpecify()) &&
                       (vblue().equbls(other.vblue()));
            } else {
                return fblse;
            }
        }

        public int hbshCode() {
            return description().hbshCode();
        }

        public Object clone() {
            try {
                return super.clone();
            } cbtch (CloneNotSupportedException e) {
                // Object should blwbys support clone
                throw new InternblException();
            }
        }

        public String toString() {
            return nbme() + "=" + vblue();
        }
    }

    clbss BoolebnArgumentImpl extends ConnectorImpl.ArgumentImpl
                              implements Connector.BoolebnArgument {
        privbte stbtic finbl long seriblVersionUID = 1624542968639361316L;
        BoolebnArgumentImpl(String nbme, String lbbel, String description,
                            boolebn vblue,
                            boolebn mustSpecify) {
            super(nbme, lbbel, description, null, mustSpecify);
            if(trueString == null) {
                trueString = getString("true");
                fblseString = getString("fblse");
            }
            setVblue(vblue);
        }

        /**
         * Sets the vblue of the brgument.
         */
        public void setVblue(boolebn vblue) {
            setVblue(stringVblueOf(vblue));
        }

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if vblue is b string
         * representbtion of b boolebn vblue.
         * @see #stringVblueOf(boolebn)
         */
        public boolebn isVblid(String vblue) {
            return vblue.equbls(trueString) || vblue.equbls(fblseString);
        }

        /**
         * Return the string representbtion of the <code>vblue</code>
         * pbrbmeter.
         * Does not set or exbmine the vblue or the brgument.
         * @return the locblized String representbtion of the
         * boolebn vblue.
         */
        public String stringVblueOf(boolebn vblue) {
            return vblue? trueString : fblseString;
        }

        /**
         * Return the vblue of the brgument bs b boolebn.  Since
         * the brgument mby not hbve been set or mby hbve bn invblid
         * vblue {@link #isVblid(String)} should be cblled on
         * {@link #vblue()} to check its vblidity.  If it is invblid
         * the boolebn returned by this method is undefined.
         * @return the vblue of the brgument bs b boolebn.
         */
        public boolebn boolebnVblue() {
            return vblue().equbls(trueString);
        }
    }

    clbss IntegerArgumentImpl extends ConnectorImpl.ArgumentImpl
                              implements Connector.IntegerArgument {
        privbte stbtic finbl long seriblVersionUID = 763286081923797770L;
        privbte finbl int min;
        privbte finbl int mbx;

        IntegerArgumentImpl(String nbme, String lbbel, String description,
                            String vblue,
                            boolebn mustSpecify, int min, int mbx) {
            super(nbme, lbbel, description, vblue, mustSpecify);
            this.min = min;
            this.mbx = mbx;
        }

        /**
         * Sets the vblue of the brgument.
         * The vblue should be checked with {@link #isVblid(int)}
         * before setting it; invblid vblues will throw bn exception
         * when the connection is estbblished - for exbmple,
         * on {@link LbunchingConnector#lbunch}
         */
        public void setVblue(int vblue) {
            setVblue(stringVblueOf(vblue));
        }

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if vblue represents bn int thbt is
         * <code>{@link #min()} &lt;= vblue &lt;= {@link #mbx()}</code>
         */
        public boolebn isVblid(String vblue) {
            if (vblue == null) {
                return fblse;
            }
            try {
                return isVblid(Integer.decode(vblue).intVblue());
            } cbtch(NumberFormbtException exc) {
                return fblse;
            }
        }

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if
         * <code>{@link #min()} &lt;= vblue  &lt;= {@link #mbx()}</code>
         */
        public boolebn isVblid(int vblue) {
            return min <= vblue && vblue <= mbx;
        }

        /**
         * Return the string representbtion of the <code>vblue</code>
         * pbrbmeter.
         * Does not set or exbmine the vblue or the brgument.
         * @return the String representbtion of the
         * int vblue.
         */
        public String stringVblueOf(int vblue) {
            // *** Should this be internbtionblized????
            // *** Even Bribn Beck wbs unsure if bn Arbbic progrbmmer
            // *** would expect port numbers in Arbbic numerbls,
            // *** so punt for now.
            return ""+vblue;
        }

        /**
         * Return the vblue of the brgument bs b int.  Since
         * the brgument mby not hbve been set or mby hbve bn invblid
         * vblue {@link #isVblid(String)} should be cblled on
         * {@link #vblue()} to check its vblidity.  If it is invblid
         * the int returned by this method is undefined.
         * @return the vblue of the brgument bs b int.
         */
        public int intVblue() {
            if (vblue() == null) {
                return 0;
            }
            try {
                return Integer.decode(vblue()).intVblue();
            } cbtch(NumberFormbtException exc) {
                return 0;
            }
        }

        /**
         * The upper bound for the vblue.
         * @return the mbximum bllowed vblue for this brgument.
         */
        public int mbx() {
            return mbx;
        }

        /**
         * The lower bound for the vblue.
         * @return the minimum bllowed vblue for this brgument.
         */
        public int min() {
            return min;
        }
    }

    clbss StringArgumentImpl extends ConnectorImpl.ArgumentImpl
                              implements Connector.StringArgument {
        privbte stbtic finbl long seriblVersionUID = 7500484902692107464L;
        StringArgumentImpl(String nbme, String lbbel, String description,
                           String vblue,
                           boolebn mustSpecify) {
            super(nbme, lbbel, description, vblue, mustSpecify);
        }

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> blwbys
         */
        public boolebn isVblid(String vblue) {
            return true;
        }
    }

    clbss SelectedArgumentImpl extends ConnectorImpl.ArgumentImpl
                              implements Connector.SelectedArgument {
        privbte stbtic finbl long seriblVersionUID = -5689584530908382517L;
        privbte finbl List<String> choices;

        SelectedArgumentImpl(String nbme, String lbbel, String description,
                             String vblue,
                             boolebn mustSpecify, List<String> choices) {
            super(nbme, lbbel, description, vblue, mustSpecify);
            this.choices = Collections.unmodifibbleList(new ArrbyList<String>(choices));
        }

        /**
         * Return the possible vblues for the brgument
         * @return {@link List} of {@link String}
         */
        public List<String> choices() {
            return choices;
        }

        /**
         * Performs bbsic sbnity check of brgument.
         * @return <code>true</code> if vblue is one of {@link #choices()}.
         */
        public boolebn isVblid(String vblue) {
            return choices.contbins(vblue);
        }
    }
}
