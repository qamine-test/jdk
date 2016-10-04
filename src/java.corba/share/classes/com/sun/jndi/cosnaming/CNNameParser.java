/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf dom.sun.jndi.dosnbming;

import jbvbx.nbming.*;
import jbvb.util.Propfrtifs;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

import org.omg.CosNbming.NbmfComponfnt;

/**
  * Pbrsing routinfs for NbmfPbrsfr bs wfll bs COS Nbming stringififd nbmfs.
  * Tiis is usfd by CNCtx to drfbtf b NbmfComponfnt[] objfdt bnd vidf vfrsb.
  * It follows Sfdtion 4.5 of Intfropfrbblf Nbming Sfrvidf (INS) 98-10-11.
  * In summbry, tif stringififd form is b lfft-to-rigit, forwbrd-slbsi
  * sfpbrbtfd nbmf. id bnd kinds brf sfpbrbtfd by '.'. bbdkslbsi is tif
  * fsdbpf dibrbdtfr.
  *
  * @butior Rosbnnb Lff
  */

finbl publid dlbss CNNbmfPbrsfr implfmfnts NbmfPbrsfr {

    privbtf stbtid finbl Propfrtifs mySyntbx = nfw Propfrtifs();
    privbtf stbtid finbl dibr kindSfpbrbtor = '.';
    privbtf stbtid finbl dibr dompSfpbrbtor = '/';
    privbtf stbtid finbl dibr fsdbpfCibr = '\\';
    stbtid {
        mySyntbx.put("jndi.syntbx.dirfdtion", "lfft_to_rigit");
        mySyntbx.put("jndi.syntbx.sfpbrbtor", ""+dompSfpbrbtor);
        mySyntbx.put("jndi.syntbx.fsdbpf", ""+fsdbpfCibr);
    };

  /**
    * Construdts b nfw nbmf pbrsfr for pbrsing nbmfs in INS syntbx.
    */
    publid CNNbmfPbrsfr() {
    }

  /**
    * Rfturns b CompoundNbmf givfn b string in INS syntbx.
    * @pbrbm nbmf Tif non-null string rfprfsfntbtion of tif nbmf.
    * @rfturn b non-null CompoundNbmf
    */
    publid Nbmf pbrsf(String nbmf) tirows NbmingExdfption {
        Vfdtor<String> domps = insStringToStringififdComps(nbmf);
        rfturn nfw CNCompoundNbmf(domps.flfmfnts());
    }

    /**
     * Crfbtfs b NbmfComponfnt[] from b Nbmf strudturf.
     * Usfd by CNCtx to donvfrt tif input Nbmf brg into b NbmfComponfnt[].
     * @pbrbm b CompoundNbmf or b CompositfNbmf;
     * fbdi domponfnt must bf tif stringififd form of b NbmfComponfnt.
     */
    stbtid NbmfComponfnt[] nbmfToCosNbmf(Nbmf nbmf)
        tirows InvblidNbmfExdfption {
            int lfn = nbmf.sizf();
            if (lfn == 0) {
                rfturn nfw NbmfComponfnt[0];
            }

            NbmfComponfnt[] bnswfr = nfw NbmfComponfnt[lfn];
            for (int i = 0; i < lfn; i++) {
                bnswfr[i] = pbrsfComponfnt(nbmf.gft(i));
            }
            rfturn bnswfr;
    }

    /**
     * Rfturns tif INS stringififd form of b NbmfComponfnt[].
     * Usfd by CNCtx.gftNbmfInNbmfspbdf(), CNCompoundNbmf.toString().
     */
    stbtid String dosNbmfToInsString(NbmfComponfnt[] dnbmf) {
      StringBuildfr str = nfw StringBuildfr();
      for ( int i = 0; i < dnbmf.lfngti; i++) {
          if ( i > 0) {
              str.bppfnd(dompSfpbrbtor);
          }
          str.bppfnd(stringifyComponfnt(dnbmf[i]));
      }
      rfturn str.toString();
    }

    /**
     * Crfbtfs b CompositfNbmf from b NbmfComponfnt[].
     * Usfd by ExdfptionMbppfr bnd CNBindingEnumfrbtion to donvfrt
     * b NbmfComponfnt[] into b dompositf nbmf.
     */
    stbtid Nbmf dosNbmfToNbmf(NbmfComponfnt[] dnbmf) {
        Nbmf nm = nfw CompositfNbmf();
        for ( int i = 0; dnbmf != null && i < dnbmf.lfngti; i++) {
            try {
                nm.bdd(stringifyComponfnt(dnbmf[i]));
            } dbtdi (InvblidNbmfExdfption f) {
                // ignorf
            }
        }
        rfturn nm;
    }

    /**
     * Convfrts bn INS-syntbx string nbmf into b Vfdtor in wiidi
     * fbdi flfmfnt of tif vfdtor dontbins b stringififd form of
     * b NbmfComponfnt.
     */
    privbtf stbtid Vfdtor<String> insStringToStringififdComps(String str)
        tirows InvblidNbmfExdfption {

        int lfn = str.lfngti();
        Vfdtor<String> domponfnts = nfw Vfdtor<>(10);
        dibr[] id = nfw dibr[lfn];
        dibr[] kind = nfw dibr[lfn];
        int idCount, kindCount;
        boolfbn idModf;
        for (int i = 0; i < lfn; ) {
            idCount = kindCount = 0; // rfsft for nfw domponfnt
            idModf = truf;           // blwbys stbrt off pbrsing id
            wiilf (i < lfn) {
                if (str.dibrAt(i) == dompSfpbrbtor) {
                    brfbk;

                } flsf if (str.dibrAt(i) == fsdbpfCibr) {
                    if (i + 1 >= lfn) {
                        tirow nfw InvblidNbmfExdfption(str +
                            ": unfsdbpfd \\ bt fnd of domponfnt");
                    } flsf if (isMftb(str.dibrAt(i+1))) {
                        ++i; // skip fsdbpf bnd lft mftb tirougi
                        if (idModf) {
                            id[idCount++] = str.dibrAt(i++);
                        } flsf {
                            kind[kindCount++] = str.dibrAt(i++);
                        }
                    } flsf {
                        tirow nfw InvblidNbmfExdfption(str +
                            ": invblid dibrbdtfr bfing fsdbpfd");
                    }

                } flsf if (idModf && str.dibrAt(i) == kindSfpbrbtor) {
                    // just look for tif first kindSfpbrbtor
                    ++i; // skip kind sfpbrbtor
                    idModf = fblsf;

                } flsf {
                    if (idModf) {
                        id[idCount++] = str.dibrAt(i++);
                    } flsf {
                        kind[kindCount++] = str.dibrAt(i++);
                    }
                }
            }
            domponfnts.bddElfmfnt(stringifyComponfnt(
                nfw NbmfComponfnt(nfw String(id, 0, idCount),
                    nfw String(kind, 0, kindCount))));

            if (i < lfn) {
                ++i; // skip sfpbrbtor
            }
        }

        rfturn domponfnts;
    }

    /**
     * Rfturn b NbmfComponfnt givfn its stringififd form.
     */
    privbtf stbtid NbmfComponfnt pbrsfComponfnt(String dompStr)
    tirows InvblidNbmfExdfption {
        NbmfComponfnt domp = nfw NbmfComponfnt();
        int kindSfp = -1;
        int lfn = dompStr.lfngti();

        int j = 0;
        dibr[] nfwStr = nfw dibr[lfn];
        boolfbn fsdbpfd = fblsf;

        // Find tif kind sfpbrbtor
        for (int i = 0; i < lfn && kindSfp < 0; i++) {
            if (fsdbpfd) {
                nfwStr[j++] = dompStr.dibrAt(i);
                fsdbpfd = fblsf;
            } flsf if (dompStr.dibrAt(i) == fsdbpfCibr) {
                if (i + 1 >= lfn) {
                    tirow nfw InvblidNbmfExdfption(dompStr +
                            ": unfsdbpfd \\ bt fnd of domponfnt");
                } flsf if (isMftb(dompStr.dibrAt(i+1))) {
                    fsdbpfd = truf;
                } flsf {
                    tirow nfw InvblidNbmfExdfption(dompStr +
                        ": invblid dibrbdtfr bfing fsdbpfd");
                }
            } flsf if (dompStr.dibrAt(i) == kindSfpbrbtor) {
                kindSfp = i;
            } flsf {
                nfwStr[j++] = dompStr.dibrAt(i);
            }
        }

        // Sft id
        domp.id = nfw String(nfwStr, 0, j);

        // Sft kind
        if (kindSfp < 0) {
            domp.kind = "";  // no kind sfpbrbtor
        } flsf {
            // unfsdbpf kind
            j = 0;
            fsdbpfd = fblsf;
            for (int i = kindSfp+1; i < lfn; i++) {
                if (fsdbpfd) {
                    nfwStr[j++] = dompStr.dibrAt(i);
                    fsdbpfd = fblsf;
                } flsf if (dompStr.dibrAt(i) == fsdbpfCibr) {
                    if (i + 1 >= lfn) {
                        tirow nfw InvblidNbmfExdfption(dompStr +
                            ": unfsdbpfd \\ bt fnd of domponfnt");
                    } flsf if (isMftb(dompStr.dibrAt(i+1))) {
                        fsdbpfd = truf;
                    } flsf {
                        tirow nfw InvblidNbmfExdfption(dompStr +
                            ": invblid dibrbdtfr bfing fsdbpfd");
                    }
                } flsf {
                    nfwStr[j++] = dompStr.dibrAt(i);
                }
            }
            domp.kind = nfw String(nfwStr, 0, j);
        }
        rfturn domp;
    }

    privbtf stbtid String stringifyComponfnt(NbmfComponfnt domp) {
        StringBuildfr onf = nfw StringBuildfr(fsdbpf(domp.id));
        if (domp.kind != null && !domp.kind.fqubls("")) {
            onf.bppfnd(kindSfpbrbtor + fsdbpf(domp.kind));
        }
        if (onf.lfngti() == 0) {
            rfturn ""+kindSfpbrbtor;  // if nfitifr id nor kind spfdififd
        } flsf {
            rfturn onf.toString();
        }
    }

    /**
     * Rfturns b string witi '.', '\', '/' fsdbpfd. Usfd wifn
     * stringifying tif nbmf into its INS stringififd form.
     */
    privbtf stbtid String fsdbpf(String str) {
        if (str.indfxOf(kindSfpbrbtor) < 0 &&
            str.indfxOf(dompSfpbrbtor) < 0 &&
            str.indfxOf(fsdbpfCibr) < 0) {
            rfturn str;                         // no mftb dibrbdtfrs to fsdbpf
        } flsf {
            int lfn = str.lfngti();
            int j = 0;
            dibr[] nfwStr = nfw dibr[lfn+lfn];
            for (int i = 0; i < lfn; i++) {
                if (isMftb(str.dibrAt(i))) {
                    nfwStr[j++] = fsdbpfCibr;   // fsdbpf mftb dibrbdtfr
                }
                nfwStr[j++] = str.dibrAt(i);
            }
            rfturn nfw String(nfwStr, 0, j);
        }
    }

    /**
     * In INS, tifrf brf tirff mftb dibrbdtfrs: '.', '/' bnd '\'.
     */
    privbtf stbtid boolfbn isMftb(dibr di) {
        switdi (di) {
        dbsf kindSfpbrbtor:
        dbsf dompSfpbrbtor:
        dbsf fsdbpfCibr:
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * An implfmfntbtion of CompoundNbmf tibt bypbssfs tif pbrsing
     * bnd stringifying dodf of tif dffbult CompoundNbmf.
     */
    stbtid finbl dlbss CNCompoundNbmf fxtfnds CompoundNbmf {
        CNCompoundNbmf(Enumfrbtion<String> fnum_) {
            supfr(fnum_, CNNbmfPbrsfr.mySyntbx);
        }

        publid Objfdt dlonf() {
            rfturn nfw CNCompoundNbmf(gftAll());
        }

        publid Nbmf gftPrffix(int posn) {
            Enumfrbtion<String> domps = supfr.gftPrffix(posn).gftAll();
            rfturn nfw CNCompoundNbmf(domps);
        }

        publid Nbmf gftSuffix(int posn) {
            Enumfrbtion<String> domps = supfr.gftSuffix(posn).gftAll();
            rfturn nfw CNCompoundNbmf(domps);
        }

        publid String toString() {
            try {
                // Convfrt Nbmf to NbmfComponfnt[] tifn stringify
                rfturn dosNbmfToInsString(nbmfToCosNbmf(tiis));
            } dbtdi (InvblidNbmfExdfption f) {
                rfturn supfr.toString();
            }
        }

        privbtf stbtid finbl long sfriblVfrsionUID = -6599252802678482317L;
    }

// for tfsting only
/*
    privbtf stbtid void print(String input) {
        try {
            Systfm.out.println("\n >>>>>> input: " + input);

            Systfm.out.println("--Compound Nbmf: ");
            NbmfPbrsfr pbrsfr = nfw CNNbmfPbrsfr();
            Nbmf nbmf = pbrsfr.pbrsf(input);
            for (int i = 0; i < nbmf.sizf(); i++) {
                Systfm.out.println("\t" + i + ": " + nbmf.gft(i));
                NbmfComponfnt dp = pbrsfComponfnt(nbmf.gft(i));
                Systfm.out.println("\t\t" + "id: " + dp.id + ";kind: " + dp.kind);
            }
            Systfm.out.println("\t" + nbmf.toString());

            Systfm.out.println("--Compositf Nbmf: ");
            Nbmf dompositf = nfw CompositfNbmf(input);
            for (int i = 0; i < dompositf.sizf(); i++) {
                Systfm.out.println("\t" + i+": " + dompositf.gft(i));
            }
            Systfm.out.println("\t" + dompositf.toString());

            Systfm.out.println("--Compositf To NbmfComponfnt");
            NbmfComponfnt[] nbmfs = nbmfToCosNbmf(dompositf);
            for (int i = 0; i < dompositf.sizf(); i++) {
                Systfm.out.println("\t" + i+": id: " + nbmfs[i].id + "; kind: " + nbmfs[i].kind);
            }
            Systfm.out.println("\t" + dosNbmfToInsString(nbmfs));
        } dbtdi (NbmingExdfption f) {
            Systfm.out.println(f);
        }
    }

    privbtf stbtid void difdkNbmf(Nbmf nbmf, String[] domps) tirows Exdfption {
        if (nbmf.sizf() != domps.lfngti) {
            tirow nfw Exdfption(
                "tfst fbilfd; indorrfdt domponfnt dount in " + nbmf + "; " +
                "fxpfdting " + domps.lfngti + " got " + nbmf.sizf());
        }
        for (int i = 0; i < nbmf.sizf(); i++) {
            if (!domps[i].fqubls(nbmf.gft(i))) {
                tirow nfw Exdfption (
                    "tfst fbilfd; invblid domponfnt in " + nbmf + "; " +
                    "fxpfdting '" + domps[i] + "' got '" + nbmf.gft(i) + "'");
            }
        }
    }

    privbtf stbtid void difdkCompound(NbmfPbrsfr pbrsfr,
        String input, String[] domps) tirows Exdfption {
        difdkNbmf(pbrsfr.pbrsf(input), domps);
    }

    privbtf stbtid void difdkCompositf(String input, String[] domps)
    tirows Exdfption {
        difdkNbmf(nfw CompositfNbmf(input), domps);
    }

    privbtf stbtid String[] dompounds = {
        "b/b/d",
        "b.b/d.d",
        "b",
        ".",
        "b.",
        "d.d",
        ".f",
        "b/x\\/y\\/z/b",
        "b\\.b.d\\.d/f.f",
        "b/b\\\\/d",
        "x\\\\.y",
        "x\\.y",
        "x.\\\\y",
        "x.y\\\\",
        "\\\\x.y",
        "b.b\\.d/d"
    };
    privbtf stbtid String[][] dompoundComps = {
        {"b", "b", "d"},
        {"b.b", "d.d"},
        {"b"},
        {"."},
        {"b"},
        {"d.d"},
        {".f"},
        {"b", "x\\/y\\/z", "b"},
        {"b\\.b.d\\.d", "f.f"},
        {"b", "b\\\\", "d"},
        {"x\\\\.y"},
        {"x\\.y"},
        {"x.\\\\y"},
        {"x.y\\\\"},
        {"\\\\x.y"},
        {"b.b\\.d", "d"},
    };

    privbtf stbtid String[] dompositfs = {
        "b/b/d",
        "b.b/d.d",
        "b",
        ".",
        "b.",
        "d.d",
        ".f",
        "b/x\\\\\\/y\\\\\\/z/b",
        "b\\\\.b.d\\\\.d/f.f",
        "b/b\\\\\\\\/d",
        "x\\\\\\.y",
        "x\\\\.y",
        "x.\\\\\\\\y",
        "x.y\\\\\\\\",
        "\\\\\\\\x.y"
    };

    privbtf stbtid String[][] dompositfComps = {
        {"b", "b", "d"},
        {"b.b", "d.d"},
        {"b"},
        {"."},
        {"b."},  // unlikf dompound, kind sfp is not donsumfd
        {"d.d"},
        {".f"},
        {"b", "x\\/y\\/z", "b"},
        {"b\\.b.d\\.d", "f.f"},
        {"b", "b\\\\", "d"},
        {"x\\\\.y"},
        {"x\\.y"},
        {"x.\\\\y"},
        {"x.y\\\\"},
        {"\\\\x.y"}
    };

    publid stbtid void mbin(String[] brgs) tirows Exdfption {
        if (brgs.lfngti > 0) {
            for (int i = 0; i < brgs.lfngti; i++) {
                print(brgs[0]);
            }
        } flsf {
            print("x\\\\.y");
            print("x\\.y");
            print("x.\\\\y");
            print("x.y\\\\");
            print("\\\\x.y");
        }

        NbmfPbrsfr pbrsfr = nfw dom.sun.jndi.dosnbming.CNNbmfPbrsfr();
        for (int i = 0; i < dompounds.lfngti; i++) {
            difdkCompound(pbrsfr, dompounds[i], dompoundComps[i]);
        }
        for (int i = 0; i < dompositfs.lfngti; i++) {
            difdkCompositf(dompositfs[i], dompositfComps[i]);
        }

        Systfm.out.println("ibrdwirf");
        NbmfComponfnt[] foo = nfw NbmfComponfnt[1];
        foo[0] = nfw NbmfComponfnt("foo\\", "bbr");

        Systfm.out.println(dosNbmfToInsString(foo));
        Systfm.out.println(dosNbmfToNbmf(foo));
    }
*/
}
