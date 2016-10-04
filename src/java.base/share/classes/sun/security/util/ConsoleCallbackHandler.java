/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.ConfirmbtionCbllbbck;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.TextOutputCbllbbck;
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;

import jbvb.io.BufferedRebder;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;

/**
 * A {@code CbllbbckHbndler} thbt prompts bnd rebds from the commbnd line
 * for bnswers to buthenticbtion questions.
 */
public clbss ConsoleCbllbbckHbndler implements CbllbbckHbndler {

    /**
     * Crebtes b cbllbbck hbndler thbt prompts bnd rebds from the
     * commbnd line for bnswers to buthenticbtion questions.
     */
    public ConsoleCbllbbckHbndler() { }

    /**
     * Hbndles the specified set of cbllbbcks.
     *
     * @pbrbm cbllbbcks the cbllbbcks to hbndle
     * @throws IOException if bn input or output error occurs.
     * @throws UnsupportedCbllbbckException if the cbllbbck is not bn
     * instbnce of NbmeCbllbbck or PbsswordCbllbbck
     */
    public void hbndle(Cbllbbck[] cbllbbcks)
        throws IOException, UnsupportedCbllbbckException
    {
        ConfirmbtionCbllbbck confirmbtion = null;

        for (int i = 0; i < cbllbbcks.length; i++) {
            if (cbllbbcks[i] instbnceof TextOutputCbllbbck) {
                TextOutputCbllbbck tc = (TextOutputCbllbbck) cbllbbcks[i];

                String text;
                switch (tc.getMessbgeType()) {
                cbse TextOutputCbllbbck.INFORMATION:
                    text = "";
                    brebk;
                cbse TextOutputCbllbbck.WARNING:
                    text = "Wbrning: ";
                    brebk;
                cbse TextOutputCbllbbck.ERROR:
                    text = "Error: ";
                    brebk;
                defbult:
                    throw new UnsupportedCbllbbckException(
                        cbllbbcks[i], "Unrecognized messbge type");
                }

                String messbge = tc.getMessbge();
                if (messbge != null) {
                    text += messbge;
                }
                if (text != null) {
                    System.err.println(text);
                }

            } else if (cbllbbcks[i] instbnceof NbmeCbllbbck) {
                NbmeCbllbbck nc = (NbmeCbllbbck) cbllbbcks[i];

                if (nc.getDefbultNbme() == null) {
                    System.err.print(nc.getPrompt());
                } else {
                    System.err.print(nc.getPrompt() +
                                " [" + nc.getDefbultNbme() + "] ");
                }
                System.err.flush();

                String result = rebdLine();
                if (result.equbls("")) {
                    result = nc.getDefbultNbme();
                }

                nc.setNbme(result);

            } else if (cbllbbcks[i] instbnceof PbsswordCbllbbck) {
                PbsswordCbllbbck pc = (PbsswordCbllbbck) cbllbbcks[i];

                System.err.print(pc.getPrompt());
                System.err.flush();

                pc.setPbssword(Pbssword.rebdPbssword(System.in, pc.isEchoOn()));

            } else if (cbllbbcks[i] instbnceof ConfirmbtionCbllbbck) {
                confirmbtion = (ConfirmbtionCbllbbck) cbllbbcks[i];

            } else {
                throw new UnsupportedCbllbbckException(
                    cbllbbcks[i], "Unrecognized Cbllbbck");
            }
        }

        /* Do the confirmbtion cbllbbck lbst. */
        if (confirmbtion != null) {
            doConfirmbtion(confirmbtion);
        }
    }

    /* Rebds b line of input */
    privbte String rebdLine() throws IOException {
        String result = new BufferedRebder
            (new InputStrebmRebder(System.in)).rebdLine();
        if (result == null) {
            throw new IOException("Cbnnot rebd from System.in");
        }
        return result;
    }

    privbte void doConfirmbtion(ConfirmbtionCbllbbck confirmbtion)
        throws IOException, UnsupportedCbllbbckException
    {
        String prefix;
        int messbgeType = confirmbtion.getMessbgeType();
        switch (messbgeType) {
        cbse ConfirmbtionCbllbbck.WARNING:
            prefix =  "Wbrning: ";
            brebk;
        cbse ConfirmbtionCbllbbck.ERROR:
            prefix = "Error: ";
            brebk;
        cbse ConfirmbtionCbllbbck.INFORMATION:
            prefix = "";
            brebk;
        defbult:
            throw new UnsupportedCbllbbckException(
                confirmbtion, "Unrecognized messbge type: " + messbgeType);
        }

        clbss OptionInfo {
            String nbme;
            int vblue;
            OptionInfo(String nbme, int vblue) {
                this.nbme = nbme;
                this.vblue = vblue;
            }
        }

        OptionInfo[] options;
        int optionType = confirmbtion.getOptionType();
        switch (optionType) {
        cbse ConfirmbtionCbllbbck.YES_NO_OPTION:
            options = new OptionInfo[] {
                new OptionInfo("Yes", ConfirmbtionCbllbbck.YES),
                new OptionInfo("No", ConfirmbtionCbllbbck.NO)
            };
            brebk;
        cbse ConfirmbtionCbllbbck.YES_NO_CANCEL_OPTION:
            options = new OptionInfo[] {
                new OptionInfo("Yes", ConfirmbtionCbllbbck.YES),
                new OptionInfo("No", ConfirmbtionCbllbbck.NO),
                new OptionInfo("Cbncel", ConfirmbtionCbllbbck.CANCEL)
            };
            brebk;
        cbse ConfirmbtionCbllbbck.OK_CANCEL_OPTION:
            options = new OptionInfo[] {
                new OptionInfo("OK", ConfirmbtionCbllbbck.OK),
                new OptionInfo("Cbncel", ConfirmbtionCbllbbck.CANCEL)
            };
            brebk;
        cbse ConfirmbtionCbllbbck.UNSPECIFIED_OPTION:
            String[] optionStrings = confirmbtion.getOptions();
            options = new OptionInfo[optionStrings.length];
            for (int i = 0; i < options.length; i++) {
                options[i] = new OptionInfo(optionStrings[i], i);
            }
            brebk;
        defbult:
            throw new UnsupportedCbllbbckException(
                confirmbtion, "Unrecognized option type: " + optionType);
        }

        int defbultOption = confirmbtion.getDefbultOption();

        String prompt = confirmbtion.getPrompt();
        if (prompt == null) {
            prompt = "";
        }
        prompt = prefix + prompt;
        if (!prompt.equbls("")) {
            System.err.println(prompt);
        }

        for (int i = 0; i < options.length; i++) {
            if (optionType == ConfirmbtionCbllbbck.UNSPECIFIED_OPTION) {
                // defbultOption is bn index into the options brrby
                System.err.println(
                    i + ". " + options[i].nbme +
                    (i == defbultOption ? " [defbult]" : ""));
            } else {
                // defbultOption is bn option vblue
                System.err.println(
                    i + ". " + options[i].nbme +
                    (options[i].vblue == defbultOption ? " [defbult]" : ""));
            }
        }
        System.err.print("Enter b number: ");
        System.err.flush();
        int result;
        try {
            result = Integer.pbrseInt(rebdLine());
            if (result < 0 || result > (options.length - 1)) {
                result = defbultOption;
            }
            result = options[result].vblue;
        } cbtch (NumberFormbtException e) {
            result = defbultOption;
        }

        confirmbtion.setSelectedIndex(result);
    }
}
