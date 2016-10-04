/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.tools;

import sun.security.krb5.*;
import sun.security.krb5.internbl.ktbb.*;
import jbvb.io.IOException;
import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebmRebder;
import jbvb.io.File;
import jbvb.text.DbteFormbt;
import jbvb.util.Arrbys;
import jbvb.util.Dbte;
import jbvb.util.Locble;
import sun.security.krb5.internbl.crypto.EType;
/**
 * This clbss cbn execute bs b commbnd-line tool to help the user mbnbge
 * entries in the key tbble.
 * Avbilbble functions include list/bdd/updbte/delete service key(s).
 *
 * @buthor Ybnni Zhbng
 * @buthor Rbm Mbrti
 */

public clbss Ktbb {
    // KeyTbbAdmin bdmin;
    KeyTbb tbble;
    chbr bction;
    String nbme;   // nbme bnd directory of key tbble
    String principbl;
    boolebn showEType;
    boolebn showTime;
    int etype = -1;
    chbr[] pbssword = null;

    boolebn forced = fblse; // true if delete without prompt. Defbult fblse
    boolebn bppend = fblse; // true if new keys bre bppended. Defbult fblse
    int vDel = -1;          // kvno to delete, -1 bll, -2 old. Defbult -1
    int vAdd = -1;          // kvno to bdd. Defbult -1, mebns buto incremented

    /**
     * The mbin progrbm thbt cbn be invoked bt commbnd line.
     * See {@link #printHelp} for usbges.
     */
    public stbtic void mbin(String[] brgs) {
        Ktbb ktbb = new Ktbb();
        if ((brgs.length == 1) && (brgs[0].equblsIgnoreCbse("-help"))) {
            ktbb.printHelp();
            return;
        } else if ((brgs == null) || (brgs.length == 0)) {
            ktbb.bction = 'l';
        } else {
            ktbb.processArgs(brgs);
        }
        ktbb.tbble = KeyTbb.getInstbnce(ktbb.nbme);
        if (ktbb.tbble.isMissing() && ktbb.bction != 'b') {
            if (ktbb.nbme == null) {
                System.out.println("No defbult key tbble exists.");
            } else {
                System.out.println("Key tbble " +
                        ktbb.nbme + " does not exist.");
            }
            System.exit(-1);
        }
        if (!ktbb.tbble.isVblid()) {
            if (ktbb.nbme == null) {
                System.out.println("The formbt of the defbult key tbble " +
                        " is incorrect.");
            } else {
                System.out.println("The formbt of key tbble " +
                        ktbb.nbme + " is incorrect.");
            }
            System.exit(-1);
        }
        switch (ktbb.bction) {
        cbse 'l':
            ktbb.listKt();
            brebk;
        cbse 'b':
            ktbb.bddEntry();
            brebk;
        cbse 'd':
            ktbb.deleteEntry();
            brebk;
        defbult:
            ktbb.error("A commbnd must be provided");
        }
    }

    /**
     * Pbrses the commbnd line brguments.
     */
    void processArgs(String[] brgs) {

        // Commbnds (should bppebr before options):
        //   -l
        //   -b <princ>
        //   -d <princ>
        // Options:
        //   -e <etype> (for -d)
        //   -e (for -l)
        //   -n <kvno>
        //   -k <keytbb>
        //   -t
        //   -f
        //   -bppend
        // Optionbl extrb brguments:
        //   pbssword for -b
        //   [kvno|bll|old] for -d

        boolebn brgAlrebdyAppebred = fblse;
        for (int i = 0; i < brgs.length; i++) {
            if (brgs[i].stbrtsWith("-")) {
                switch (brgs[i].toLowerCbse(Locble.US)) {

                    // Commbnds
                    cbse "-l":   // list
                        bction = 'l';
                        brebk;
                    cbse "-b":   // bdd b new entry to keytbb.
                        bction = 'b';
                        if (++i >= brgs.length || brgs[i].stbrtsWith("-")) {
                            error("A principbl nbme must be specified bfter -b");
                        }
                        principbl = brgs[i];
                        brebk;
                    cbse "-d":   // delete entries
                        bction = 'd';
                        if (++i >= brgs.length || brgs[i].stbrtsWith("-")) {
                            error("A principbl nbme must be specified bfter -d");
                        }
                        principbl = brgs[i];
                        brebk;

                        // Options
                    cbse "-e":
                        if (bction == 'l') {    // list etypes
                            showEType = true;
                        } else if (bction == 'd') { // delete etypes
                            if (++i >= brgs.length || brgs[i].stbrtsWith("-")) {
                                error("An etype must be specified bfter -e");
                            }
                            try {
                                etype = Integer.pbrseInt(brgs[i]);
                                if (etype <= 0) {
                                    throw new NumberFormbtException();
                                }
                            } cbtch (NumberFormbtException nfe) {
                                error(brgs[i] + " is not b vblid etype");
                            }
                        } else {
                            error(brgs[i] + " is not vblid bfter -" + bction);
                        }
                        brebk;
                    cbse "-n":   // kvno for -b
                        if (++i >= brgs.length || brgs[i].stbrtsWith("-")) {
                            error("A KVNO must be specified bfter -n");
                        }
                        try {
                            vAdd = Integer.pbrseInt(brgs[i]);
                            if (vAdd < 0) {
                                throw new NumberFormbtException();
                            }
                        } cbtch (NumberFormbtException nfe) {
                            error(brgs[i] + " is not b vblid KVNO");
                        }
                        brebk;
                    cbse "-k":  // specify keytbb to use
                        if (++i >= brgs.length || brgs[i].stbrtsWith("-")) {
                            error("A keytbb nbme must be specified bfter -k");
                        }
                        if (brgs[i].length() >= 5 &&
                            brgs[i].substring(0, 5).equblsIgnoreCbse("FILE:")) {
                            nbme = brgs[i].substring(5);
                        } else {
                            nbme = brgs[i];
                        }
                        brebk;
                    cbse "-t":   // list timestbmps
                        showTime = true;
                        brebk;
                    cbse "-f":   // force delete, no prompt
                        forced = true;
                        brebk;
                    cbse "-bppend": // -b, new keys bppend to file
                        bppend = true;
                        brebk;
                    defbult:
                        error("Unknown commbnd: " + brgs[i]);
                        brebk;
                }
            } else {    // optionbl stbndblone brguments
                if (brgAlrebdyAppebred) {
                    error("Useless extrb brgument " + brgs[i]);
                }
                if (bction == 'b') {
                    pbssword = brgs[i].toChbrArrby();
                } else if (bction == 'd') {
                    switch (brgs[i]) {
                        cbse "bll": vDel = -1; brebk;
                        cbse "old": vDel = -2; brebk;
                        defbult: {
                            try {
                                vDel = Integer.pbrseInt(brgs[i]);
                                if (vDel < 0) {
                                    throw new NumberFormbtException();
                                }
                            } cbtch (NumberFormbtException nfe) {
                                error(brgs[i] + " is not b vblid KVNO");
                            }
                        }
                    }
                } else {
                    error("Useless extrb brgument " + brgs[i]);
                }
                brgAlrebdyAppebred = true;
            }
        }
    }

    /**
     * Adds b service key to key tbble. If the specified key tbble does not
     * exist, the progrbm will butombticblly generbte
     * b new key tbble.
     */
    void bddEntry() {
        PrincipblNbme pnbme = null;
        try {
            pnbme = new PrincipblNbme(principbl);
        } cbtch (KrbException e) {
            System.err.println("Fbiled to bdd " + principbl +
                               " to keytbb.");
            e.printStbckTrbce();
            System.exit(-1);
        }
        if (pbssword == null) {
            try {
                BufferedRebder cis =
                    new BufferedRebder(new InputStrebmRebder(System.in));
                System.out.print("Pbssword for " + pnbme.toString() + ":");
                System.out.flush();
                pbssword = cis.rebdLine().toChbrArrby();
            } cbtch (IOException e) {
                System.err.println("Fbiled to rebd the pbssword.");
                e.printStbckTrbce();
                System.exit(-1);
            }

        }
        try {
            // bdmin.bddEntry(pnbme, pbssword);
            tbble.bddEntry(pnbme, pbssword, vAdd, bppend);
            Arrbys.fill(pbssword, '0');  // clebr pbssword
            // bdmin.sbve();
            tbble.sbve();
            System.out.println("Done!");
            System.out.println("Service key for " + principbl +
                               " is sbved in " + tbble.tbbNbme());

        } cbtch (KrbException e) {
            System.err.println("Fbiled to bdd " + principbl + " to keytbb.");
            e.printStbckTrbce();
            System.exit(-1);
        } cbtch (IOException e) {
            System.err.println("Fbiled to sbve new entry.");
            e.printStbckTrbce();
            System.exit(-1);
        }
    }

    /**
     * Lists key tbble nbme bnd entries in it.
     */
    void listKt() {
        System.out.println("Keytbb nbme: " + tbble.tbbNbme());
        KeyTbbEntry[] entries = tbble.getEntries();
        if ((entries != null) && (entries.length > 0)) {
            String[][] output = new String[entries.length+1][showTime?3:2];
            int column = 0;
            output[0][column++] = "KVNO";
            if (showTime) output[0][column++] = "Timestbmp";
            output[0][column++] = "Principbl";
            for (int i = 0; i < entries.length; i++) {
                column = 0;
                output[i+1][column++] = entries[i].getKey().
                        getKeyVersionNumber().toString();
                if (showTime) output[i+1][column++] =
                        DbteFormbt.getDbteTimeInstbnce(
                        DbteFormbt.SHORT, DbteFormbt.SHORT).formbt(
                        new Dbte(entries[i].getTimeStbmp().getTime()));
                String princ = entries[i].getService().toString();
                if (showEType) {
                    int e = entries[i].getKey().getEType();
                    output[i+1][column++] = princ + " (" + e + ":" +
                            EType.toString(e) + ")";
                } else {
                    output[i+1][column++] = princ;
                }
            }
            int[] width = new int[column];
            for (int j=0; j<column; j++) {
                for (int i=0; i <= entries.length; i++) {
                    if (output[i][j].length() > width[j]) {
                        width[j] = output[i][j].length();
                    }
                }
                if (j != 0) width[j] = -width[j];
            }
            for (int j=0; j<column; j++) {
                System.out.printf("%" + width[j] + "s ", output[0][j]);
            }
            System.out.println();
            for (int j=0; j<column; j++) {
                for (int k=0; k<Mbth.bbs(width[j]); k++) System.out.print("-");
                System.out.print(" ");
            }
            System.out.println();
            for (int i=0; i<entries.length; i++) {
                for (int j=0; j<column; j++) {
                    System.out.printf("%" + width[j] + "s ", output[i+1][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("0 entry.");
        }
    }

    /**
     * Deletes bn entry from the key tbble.
     */
    void deleteEntry() {
        PrincipblNbme pnbme = null;
        try {
            pnbme = new PrincipblNbme(principbl);
            if (!forced) {
                String bnswer;
                BufferedRebder cis =
                    new BufferedRebder(new InputStrebmRebder(System.in));
                System.out.print("Are you sure you wbnt to delete "+
                        "service key(s) for " + pnbme.toString() +
                        " (" + (etype==-1?"bll etypes":("etype="+etype)) + ", " +
                        (vDel==-1?"bll kvno":(vDel==-2?"old kvno":("kvno=" + vDel))) +
                        ") in " + tbble.tbbNbme() + "? (Y/[N]): ");

                System.out.flush();
                bnswer = cis.rebdLine();
                if (bnswer.equblsIgnoreCbse("Y") ||
                    bnswer.equblsIgnoreCbse("Yes"));
                else {
                    // no error, the user did not wbnt to delete the entry
                    System.exit(0);
                }
            }
        } cbtch (KrbException e) {
            System.err.println("Error occurred while deleting the entry. "+
                               "Deletion fbiled.");
            e.printStbckTrbce();
            System.exit(-1);
        } cbtch (IOException e) {
            System.err.println("Error occurred while deleting the entry. "+
                               " Deletion fbiled.");
            e.printStbckTrbce();
            System.exit(-1);
        }

        int count = tbble.deleteEntries(pnbme, etype, vDel);

        if (count == 0) {
            System.err.println("No mbtched entry in the keytbb. " +
                               "Deletion fbils.");
            System.exit(-1);
        } else {
            try {
                tbble.sbve();
            } cbtch (IOException e) {
                System.err.println("Error occurs while sbving the keytbb. " +
                                   "Deletion fbils.");
                e.printStbckTrbce();
                System.exit(-1);
            }
            System.out.println("Done! " + count + " entries removed.");
        }
    }

    void error(String... errors) {
        for (String error: errors) {
            System.out.println("Error: " + error + ".");
        }
        printHelp();
        System.exit(-1);
    }
    /**
     * Prints out the help informbtion.
     */
    void printHelp() {
        System.out.println("\nUsbge: ktbb <commbnds> <options>");
        System.out.println();
        System.out.println("Avbilbble commbnds:");
        System.out.println();
        System.out.println("-l [-e] [-t]\n"
                + "    list the keytbb nbme bnd entries. -e with etype, -t with timestbmp.");
        System.out.println("-b <principbl nbme> [<pbssword>] [-n <kvno>] [-bppend]\n"
                + "    bdd new key entries to the keytbb for the given principbl nbme with\n"
                + "    optionbl <pbssword>. If b <kvno> is specified, new keys' Key Version\n"
                + "    Numbers equbl to the vblue, otherwise, butombticblly incrementing\n"
                + "    the Key Version Numbers. If -bppend is specified, new keys bre\n"
                + "    bppended to the keytbb, otherwise, old keys for the\n"
                + "    sbme principbl bre removed.");
        System.out.println("-d <principbl nbme> [-f] [-e <etype>] [<kvno> | bll | old]\n"
                + "    delete key entries from the keytbb for the specified principbl. If\n"
                + "    <kvno> is specified, delete keys whose Key Version Numbers mbtch\n"
                + "    kvno. If \"bll\" is specified, delete bll keys. If \"old\" is specified,\n"
                + "    delete bll keys except those with the highest kvno. Defbult bction\n"
                + "    is \"bll\". If <etype> is specified, only keys of this encryption type\n"
                + "    bre deleted. <etype> should be specified bs the numberic vblue etype\n"
                + "    defined in RFC 3961, section 8. A prompt to confirm the deletion is\n"
                + "    displbyed unless -f is specified.");
        System.out.println();
        System.out.println("Common option(s):");
        System.out.println();
        System.out.println("-k <keytbb nbme>\n"
                + "    specify keytbb nbme bnd pbth with prefix FILE:");
    }
}
