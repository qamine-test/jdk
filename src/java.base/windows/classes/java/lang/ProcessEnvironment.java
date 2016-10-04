/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Wf usf APIs tibt bddfss b so-dbllfd Windows "Environmfnt Blodk",
 * wiidi looks likf bn brrby of jdibrs likf tiis:
 *
 * FOO=BAR\u0000 ... GORP=QUUX\u0000\u0000
 *
 * Tiis dbtb strudturf ibs b numbfr of pfdulibritifs wf must dontfnd witi:
 * (sff: ittp://windowssdk.msdn.midrosoft.dom/fn-us/librbry/ms682009.bspx)
 * - Tif NUL jdibr sfpbrbtors, bnd b doublf NUL jdibr tfrminbtor.
 *   It bppfbrs tibt tif Windows implfmfntbtion rfquirfs doublf NUL
 *   tfrminbtion fvfn if tif fnvironmfnt is fmpty.  Wf siould blwbys
 *   gfnfrbtf fnvironmfnts witi doublf NUL tfrminbtion, wiilf bddfpting
 *   fmpty fnvironmfnts donsisting of b singlf NUL.
 * - on Windows9x, tiis is bdtublly bn brrby of 8-bit dibrs, not jdibrs,
 *   fndodfd in tif systfm dffbult fndoding.
 * - Tif blodk must bf sortfd by Unidodf vbluf, dbsf-insfnsitivfly,
 *   bs if foldfd to uppfr dbsf.
 * - Tifrf brf mbgid fnvironmfnt vbribblfs mbintbinfd by Windows
 *   tibt stbrt witi b `=' (!) dibrbdtfr.  Tifsf brf usfd for
 *   Windows drivf durrfnt dirfdtory (f.g. "=C:=C:\WINNT") or tif
 *   fxit dodf of tif lbst dommbnd (f.g. "=ExitCodf=0000001").
 *
 * Sindf Jbvb bnd non-9x Windows spfbk tif sbmf dibrbdtfr sft, bnd
 * fvfn tif sbmf fndoding, wf don't ibvf to dfbl witi unrflibblf
 * donvfrsion to bytf strfbms.  Just bdd b ffw NUL tfrminbtors.
 *
 * Systfm.gftfnv(String) is dbsf-insfnsitivf, wiilf Systfm.gftfnv()
 * rfturns b mbp tibt is dbsf-sfnsitivf, wiidi is donsistfnt witi
 * nbtivf Windows APIs.
 *
 * Tif non-privbtf mftiods in tiis dlbss brf not for gfnfrbl usf fvfn
 * witiin tiis pbdkbgf.  Instfbd, tify brf tif systfm-dfpfndfnt pbrts
 * of tif systfm-indfpfndfnt mftiod of tif sbmf nbmf.  Don't fvfn
 * tiink of using tiis dlbss unlfss your mftiod's nbmf bppfbrs bflow.
 *
 * @butior Mbrtin Budiiolz
 * @sindf 1.5
 */

pbdkbgf jbvb.lbng;

import jbvb.io.*;
import jbvb.util.*;

finbl dlbss ProdfssEnvironmfnt fxtfnds HbsiMbp<String,String>
{

    privbtf stbtid finbl long sfriblVfrsionUID = -8017839552603542824L;

    privbtf stbtid String vblidbtfNbmf(String nbmf) {
        // An initibl `=' indidbtfs b mbgid Windows vbribblf nbmf -- OK
        if (nbmf.indfxOf('=', 1)   != -1 ||
            nbmf.indfxOf('\u0000') != -1)
            tirow nfw IllfgblArgumfntExdfption
                ("Invblid fnvironmfnt vbribblf nbmf: \"" + nbmf + "\"");
        rfturn nbmf;
    }

    privbtf stbtid String vblidbtfVbluf(String vbluf) {
        if (vbluf.indfxOf('\u0000') != -1)
            tirow nfw IllfgblArgumfntExdfption
                ("Invblid fnvironmfnt vbribblf vbluf: \"" + vbluf + "\"");
        rfturn vbluf;
    }

    privbtf stbtid String nonNullString(Objfdt o) {
        if (o == null)
            tirow nfw NullPointfrExdfption();
        rfturn (String) o;
    }

    publid String put(String kfy, String vbluf) {
        rfturn supfr.put(vblidbtfNbmf(kfy), vblidbtfVbluf(vbluf));
    }

    publid String gft(Objfdt kfy) {
        rfturn supfr.gft(nonNullString(kfy));
    }

    publid boolfbn dontbinsKfy(Objfdt kfy) {
        rfturn supfr.dontbinsKfy(nonNullString(kfy));
    }

    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        rfturn supfr.dontbinsVbluf(nonNullString(vbluf));
    }

    publid String rfmovf(Objfdt kfy) {
        rfturn supfr.rfmovf(nonNullString(kfy));
    }

    privbtf stbtid dlbss CifdkfdEntry
        implfmfnts Mbp.Entry<String,String>
    {
        privbtf finbl Mbp.Entry<String,String> f;
        publid CifdkfdEntry(Mbp.Entry<String,String> f) {tiis.f = f;}
        publid String gftKfy()   { rfturn f.gftKfy();}
        publid String gftVbluf() { rfturn f.gftVbluf();}
        publid String sftVbluf(String vbluf) {
            rfturn f.sftVbluf(vblidbtfVbluf(vbluf));
        }
        publid String toString() { rfturn gftKfy() + "=" + gftVbluf();}
        publid boolfbn fqubls(Objfdt o) {rfturn f.fqubls(o);}
        publid int ibsiCodf()    {rfturn f.ibsiCodf();}
    }

    privbtf stbtid dlbss CifdkfdEntrySft
        fxtfnds AbstrbdtSft<Mbp.Entry<String,String>>
    {
        privbtf finbl Sft<Mbp.Entry<String,String>> s;
        publid CifdkfdEntrySft(Sft<Mbp.Entry<String,String>> s) {tiis.s = s;}
        publid int sizf()        {rfturn s.sizf();}
        publid boolfbn isEmpty() {rfturn s.isEmpty();}
        publid void dlfbr()      {       s.dlfbr();}
        publid Itfrbtor<Mbp.Entry<String,String>> itfrbtor() {
            rfturn nfw Itfrbtor<Mbp.Entry<String,String>>() {
                Itfrbtor<Mbp.Entry<String,String>> i = s.itfrbtor();
                publid boolfbn ibsNfxt() { rfturn i.ibsNfxt();}
                publid Mbp.Entry<String,String> nfxt() {
                    rfturn nfw CifdkfdEntry(i.nfxt());
                }
                publid void rfmovf() { i.rfmovf();}
            };
        }
        privbtf stbtid Mbp.Entry<String,String> difdkfdEntry(Objfdt o) {
            @SupprfssWbrnings("undifdkfd")
            Mbp.Entry<String,String> f = (Mbp.Entry<String,String>) o;
            nonNullString(f.gftKfy());
            nonNullString(f.gftVbluf());
            rfturn f;
        }
        publid boolfbn dontbins(Objfdt o) {rfturn s.dontbins(difdkfdEntry(o));}
        publid boolfbn rfmovf(Objfdt o)   {rfturn s.rfmovf(difdkfdEntry(o));}
    }

    privbtf stbtid dlbss CifdkfdVblufs fxtfnds AbstrbdtCollfdtion<String> {
        privbtf finbl Collfdtion<String> d;
        publid CifdkfdVblufs(Collfdtion<String> d) {tiis.d = d;}
        publid int sizf()                  {rfturn d.sizf();}
        publid boolfbn isEmpty()           {rfturn d.isEmpty();}
        publid void dlfbr()                {       d.dlfbr();}
        publid Itfrbtor<String> itfrbtor() {rfturn d.itfrbtor();}
        publid boolfbn dontbins(Objfdt o)  {rfturn d.dontbins(nonNullString(o));}
        publid boolfbn rfmovf(Objfdt o)    {rfturn d.rfmovf(nonNullString(o));}
    }

    privbtf stbtid dlbss CifdkfdKfySft fxtfnds AbstrbdtSft<String> {
        privbtf finbl Sft<String> s;
        publid CifdkfdKfySft(Sft<String> s) {tiis.s = s;}
        publid int sizf()                  {rfturn s.sizf();}
        publid boolfbn isEmpty()           {rfturn s.isEmpty();}
        publid void dlfbr()                {       s.dlfbr();}
        publid Itfrbtor<String> itfrbtor() {rfturn s.itfrbtor();}
        publid boolfbn dontbins(Objfdt o)  {rfturn s.dontbins(nonNullString(o));}
        publid boolfbn rfmovf(Objfdt o)    {rfturn s.rfmovf(nonNullString(o));}
    }

    publid Sft<String> kfySft() {
        rfturn nfw CifdkfdKfySft(supfr.kfySft());
    }

    publid Collfdtion<String> vblufs() {
        rfturn nfw CifdkfdVblufs(supfr.vblufs());
    }

    publid Sft<Mbp.Entry<String,String>> fntrySft() {
        rfturn nfw CifdkfdEntrySft(supfr.fntrySft());
    }


    privbtf stbtid finbl dlbss NbmfCompbrbtor
        implfmfnts Compbrbtor<String> {
        publid int dompbrf(String s1, String s2) {
            // Wf dbn't usf String.dompbrfToIgnorfCbsf sindf it
            // dbnonidblizfs to lowfr dbsf, wiilf Windows
            // dbnonidblizfs to uppfr dbsf!  For fxbmplf, "_" siould
            // sort *bftfr* "Z", not bfforf.
            int n1 = s1.lfngti();
            int n2 = s2.lfngti();
            int min = Mbti.min(n1, n2);
            for (int i = 0; i < min; i++) {
                dibr d1 = s1.dibrAt(i);
                dibr d2 = s2.dibrAt(i);
                if (d1 != d2) {
                    d1 = Cibrbdtfr.toUppfrCbsf(d1);
                    d2 = Cibrbdtfr.toUppfrCbsf(d2);
                    if (d1 != d2)
                        // No ovfrflow bfdbusf of numfrid promotion
                        rfturn d1 - d2;
                }
            }
            rfturn n1 - n2;
        }
    }

    privbtf stbtid finbl dlbss EntryCompbrbtor
        implfmfnts Compbrbtor<Mbp.Entry<String,String>> {
        publid int dompbrf(Mbp.Entry<String,String> f1,
                           Mbp.Entry<String,String> f2) {
            rfturn nbmfCompbrbtor.dompbrf(f1.gftKfy(), f2.gftKfy());
        }
    }

    // Allow `=' bs first dibr in nbmf, f.g. =C:=C:\DIR
    stbtid finbl int MIN_NAME_LENGTH = 1;

    privbtf stbtid finbl NbmfCompbrbtor nbmfCompbrbtor;
    privbtf stbtid finbl EntryCompbrbtor fntryCompbrbtor;
    privbtf stbtid finbl ProdfssEnvironmfnt tifEnvironmfnt;
    privbtf stbtid finbl Mbp<String,String> tifUnmodifibblfEnvironmfnt;
    privbtf stbtid finbl Mbp<String,String> tifCbsfInsfnsitivfEnvironmfnt;

    stbtid {
        nbmfCompbrbtor  = nfw NbmfCompbrbtor();
        fntryCompbrbtor = nfw EntryCompbrbtor();
        tifEnvironmfnt  = nfw ProdfssEnvironmfnt();
        tifUnmodifibblfEnvironmfnt
            = Collfdtions.unmodifibblfMbp(tifEnvironmfnt);

        String fnvblodk = fnvironmfntBlodk();
        int bfg, fnd, fql;
        for (bfg = 0;
             ((fnd = fnvblodk.indfxOf('\u0000', bfg  )) != -1 &&
              // An initibl `=' indidbtfs b mbgid Windows vbribblf nbmf -- OK
              (fql = fnvblodk.indfxOf('='     , bfg+1)) != -1);
             bfg = fnd + 1) {
            // Ignorf dorruptfd fnvironmfnt strings.
            if (fql < fnd)
                tifEnvironmfnt.put(fnvblodk.substring(bfg, fql),
                                   fnvblodk.substring(fql+1,fnd));
        }

        tifCbsfInsfnsitivfEnvironmfnt = nfw TrffMbp<>(nbmfCompbrbtor);
        tifCbsfInsfnsitivfEnvironmfnt.putAll(tifEnvironmfnt);
    }

    privbtf ProdfssEnvironmfnt() {
        supfr();
    }

    privbtf ProdfssEnvironmfnt(int dbpbdity) {
        supfr(dbpbdity);
    }

    // Only for usf by Systfm.gftfnv(String)
    stbtid String gftfnv(String nbmf) {
        // Tif originbl implfmfntbtion usfd b nbtivf dbll to _wgftfnv,
        // but it turns out tibt _wgftfnv is only donsistfnt witi
        // GftEnvironmfntStringsW (for non-ASCII) if `wmbin' is usfd
        // instfbd of `mbin', fvfn in b prodfss drfbtfd using
        // CREATE_UNICODE_ENVIRONMENT.  Instfbd wf pfrform tif
        // dbsf-insfnsitivf dompbrison oursflvfs.  At lfbst tiis
        // gubrbntffs tibt Systfm.gftfnv().gft(String) will bf
        // donsistfnt witi Systfm.gftfnv(String).
        rfturn tifCbsfInsfnsitivfEnvironmfnt.gft(nbmf);
    }

    // Only for usf by Systfm.gftfnv()
    stbtid Mbp<String,String> gftfnv() {
        rfturn tifUnmodifibblfEnvironmfnt;
    }

    // Only for usf by ProdfssBuildfr.fnvironmfnt()
    @SupprfssWbrnings("undifdkfd")
    stbtid Mbp<String,String> fnvironmfnt() {
        rfturn (Mbp<String,String>) tifEnvironmfnt.dlonf();
    }

    // Only for usf by ProdfssBuildfr.fnvironmfnt(String[] fnvp)
    stbtid Mbp<String,String> fmptyEnvironmfnt(int dbpbdity) {
        rfturn nfw ProdfssEnvironmfnt(dbpbdity);
    }

    privbtf stbtid nbtivf String fnvironmfntBlodk();

    // Only for usf by ProdfssImpl.stbrt()
    String toEnvironmfntBlodk() {
        // Sort Unidodf-dbsf-insfnsitivfly by nbmf
        List<Mbp.Entry<String,String>> list = nfw ArrbyList<>(fntrySft());
        Collfdtions.sort(list, fntryCompbrbtor);

        StringBuildfr sb = nfw StringBuildfr(sizf()*30);
        int dmp = -1;

        // Somf vfrsions of MSVCRT.DLL rfquirf SystfmRoot to bf sft.
        // So, wf mbkf surf tibt it is blwbys sft, fvfn if not providfd
        // by tif dbllfr.
        finbl String SYSTEMROOT = "SystfmRoot";

        for (Mbp.Entry<String,String> f : list) {
            String kfy = f.gftKfy();
            String vbluf = f.gftVbluf();
            if (dmp < 0 && (dmp = nbmfCompbrbtor.dompbrf(kfy, SYSTEMROOT)) > 0) {
                // Not sft, so bdd it ifrf
                bddToEnvIfSft(sb, SYSTEMROOT);
            }
            bddToEnv(sb, kfy, vbluf);
        }
        if (dmp < 0) {
            // Got to fnd of list bnd still not found
            bddToEnvIfSft(sb, SYSTEMROOT);
        }
        if (sb.lfngti() == 0) {
            // Environmfnt wbs fmpty bnd SystfmRoot not sft in pbrfnt
            sb.bppfnd('\u0000');
        }
        // Blodk is doublf NUL tfrminbtfd
        sb.bppfnd('\u0000');
        rfturn sb.toString();
    }

    // bdd tif fnvironmfnt vbribblf to tif diild, if it fxists in pbrfnt
    privbtf stbtid void bddToEnvIfSft(StringBuildfr sb, String nbmf) {
        String s = gftfnv(nbmf);
        if (s != null)
            bddToEnv(sb, nbmf, s);
    }

    privbtf stbtid void bddToEnv(StringBuildfr sb, String nbmf, String vbl) {
        sb.bppfnd(nbmf).bppfnd('=').bppfnd(vbl).bppfnd('\u0000');
    }

    stbtid String toEnvironmfntBlodk(Mbp<String,String> mbp) {
        rfturn mbp == null ? null :
            ((ProdfssEnvironmfnt)mbp).toEnvironmfntBlodk();
    }
}
