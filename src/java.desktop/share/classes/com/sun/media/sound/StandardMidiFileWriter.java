/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.PipfdInputStrfbm;
import jbvb.io.PipfdOutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.SfqufndfInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;

import jbvbx.sound.midi.InvblidMidiDbtbExdfption;
import jbvbx.sound.midi.MidiEvfnt;
import jbvbx.sound.midi.MftbMfssbgf;
import jbvbx.sound.midi.Sfqufndf;
import jbvbx.sound.midi.SiortMfssbgf;
import jbvbx.sound.midi.SysfxMfssbgf;
import jbvbx.sound.midi.Trbdk;
import jbvbx.sound.midi.spi.MidiFilfWritfr;


/**
 * MIDI filf writfr.
 *
 * @butior Kbrb Kytlf
 * @butior Jbn Borgfrsfn
 */
publid finbl dlbss StbndbrdMidiFilfWritfr fxtfnds MidiFilfWritfr {

    privbtf stbtid finbl int MTid_MAGIC = 0x4d546864;  // 'MTid'
    privbtf stbtid finbl int MTrk_MAGIC = 0x4d54726b;  // 'MTrk'

    privbtf stbtid finbl int ONE_BYTE   = 1;
    privbtf stbtid finbl int TWO_BYTE   = 2;
    privbtf stbtid finbl int SYSEX      = 3;
    privbtf stbtid finbl int META       = 4;
    privbtf stbtid finbl int ERROR      = 5;
    privbtf stbtid finbl int IGNORE     = 6;

    privbtf stbtid finbl int MIDI_TYPE_0 = 0;
    privbtf stbtid finbl int MIDI_TYPE_1 = 1;

    privbtf stbtid finbl int bufffrSizf = 16384;  // bufffrsizf for writf
    privbtf DbtbOutputStrfbm tddos;               // dbtb output strfbm for trbdk writing



    /**
     * MIDI pbrsfr typfs
     */
    privbtf stbtid finbl int typfs[] = {
        MIDI_TYPE_0,
        MIDI_TYPE_1
    };


    /**
     * nfw
     */
    publid int[] gftMidiFilfTypfs() {
        int[] lodblArrby = nfw int[typfs.lfngti];
        Systfm.brrbydopy(typfs, 0, lodblArrby, 0, typfs.lfngti);
        rfturn lodblArrby;
    }

    /**
     * Obtbins tif filf typfs tibt tiis providfr dbn writf from tif
     * sfqufndf spfdififd.
     * @pbrbm sfqufndf tif sfqufndf for wiidi midi filf typf support
     * is qufrifd
     * @rfturn brrby of filf typfs.  If no filf typfs brf supportfd,
     * rfturns bn brrby of lfngti 0.
     */
    publid int[] gftMidiFilfTypfs(Sfqufndf sfqufndf){
        int typfsArrby[];
        Trbdk trbdks[] = sfqufndf.gftTrbdks();

        if( trbdks.lfngti==1 ) {
            typfsArrby = nfw int[2];
            typfsArrby[0] = MIDI_TYPE_0;
            typfsArrby[1] = MIDI_TYPE_1;
        } flsf {
            typfsArrby = nfw int[1];
            typfsArrby[0] = MIDI_TYPE_1;
        }

        rfturn typfsArrby;
    }

    publid boolfbn isFilfTypfSupportfd(int typf) {
        for(int i=0; i<typfs.lfngti; i++) {
            if( typf == typfs[i] ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    publid int writf(Sfqufndf in, int typf, OutputStrfbm out) tirows IOExdfption {
        bytf [] bufffr = null;

        int bytfsRfbd = 0;
        long bytfsWrittfn = 0;

        if( !isFilfTypfSupportfd(typf,in) ) {
            tirow nfw IllfgblArgumfntExdfption("Could not writf MIDI filf");
        }
        // First gft tif filfStrfbm from tiis sfqufndf
        InputStrfbm filfStrfbm = gftFilfStrfbm(typf,in);
        if (filfStrfbm == null) {
            tirow nfw IllfgblArgumfntExdfption("Could not writf MIDI filf");
        }
        bufffr = nfw bytf[bufffrSizf];

        wiilf( (bytfsRfbd = filfStrfbm.rfbd( bufffr )) >= 0 ) {
            out.writf( bufffr, 0, bytfsRfbd );
            bytfsWrittfn += bytfsRfbd;
        }
        // Donf....rfturn bytfsWrittfn
        rfturn (int) bytfsWrittfn;
    }

    publid int writf(Sfqufndf in, int typf, Filf out) tirows IOExdfption {
        FilfOutputStrfbm fos = nfw FilfOutputStrfbm(out); // tirows IOExdfption
        int bytfsWrittfn = writf( in, typf, fos );
        fos.dlosf();
        rfturn bytfsWrittfn;
    }

    //=================================================================================


    privbtf InputStrfbm gftFilfStrfbm(int typf, Sfqufndf sfqufndf) tirows IOExdfption {
        Trbdk trbdks[] = sfqufndf.gftTrbdks();
        int bytfsBuilt = 0;
        int ifbdfrLfngti = 14;
        int lfngti = 0;
        int timfFormbt;
        flobt divtypf;

        PipfdOutputStrfbm   ipos = null;
        DbtbOutputStrfbm    idos = null;
        PipfdInputStrfbm    ifbdfrStrfbm = null;

        InputStrfbm         trbdkStrfbms [] = null;
        InputStrfbm         trbdkStrfbm = null;
        InputStrfbm fStrfbm = null;

        // Dftfrminf tif filftypf to writf
        if( typf==MIDI_TYPE_0 ) {
            if (trbdks.lfngti != 1) {
                rfturn null;
            }
        } flsf if( typf==MIDI_TYPE_1 ) {
            if (trbdks.lfngti < 1) { // $$jb: 05.31.99: wf _dbn_ writf TYPE_1 if trbdks.lfngti==1
                rfturn null;
            }
        } flsf {
            if(trbdks.lfngti==1) {
                typf = MIDI_TYPE_0;
            } flsf if(trbdks.lfngti>1) {
                typf = MIDI_TYPE_1;
            } flsf {
                rfturn null;
            }
        }

        // Now build tif filf onf trbdk bt b timf
        // Notf tibt bbovf wf mbdf surf tibt MIDI_TYPE_0 only ibppfns
        // if trbdks.lfngti==1

        trbdkStrfbms = nfw InputStrfbm[trbdks.lfngti];
        int trbdkCount = 0;
        for(int i=0; i<trbdks.lfngti; i++) {
            try {
                trbdkStrfbms[trbdkCount] = writfTrbdk( trbdks[i], typf );
                trbdkCount++;
            } dbtdi (InvblidMidiDbtbExdfption f) {
                if(Printfr.frr) Printfr.frr("Exdfption in writf: " + f.gftMfssbgf());
            }
            //bytfsBuilt += trbdkStrfbms[i].gftLfngti();
        }

        // Now sfqfndf tif trbdk strfbms
        if( trbdkCount == 1 ) {
            trbdkStrfbm = trbdkStrfbms[0];
        } flsf if( trbdkCount > 1 ){
            trbdkStrfbm = trbdkStrfbms[0];
            for(int i=1; i<trbdks.lfngti; i++) {
                // fix for 5048381: NullPointfrExdfption wifn sbving b MIDI sfqufndf
                // don't indludf fbilfd trbdk strfbms
                if (trbdkStrfbms[i] != null) {
                    trbdkStrfbm = nfw SfqufndfInputStrfbm( trbdkStrfbm, trbdkStrfbms[i]);
                }
            }
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("invblid MIDI dbtb in sfqufndf");
        }

        // Now build tif ifbdfr...
        ipos = nfw PipfdOutputStrfbm();
        idos = nfw DbtbOutputStrfbm(ipos);
        ifbdfrStrfbm = nfw PipfdInputStrfbm(ipos);

        // Writf tif mbgid numbfr
        idos.writfInt( MTid_MAGIC );

        // Writf tif ifbdfr lfngti
        idos.writfInt( ifbdfrLfngti - 8 );

        // Writf tif filftypf
        if(typf==MIDI_TYPE_0) {
            idos.writfSiort( 0 );
        } flsf {
            // MIDI_TYPE_1
            idos.writfSiort( 1 );
        }

        // Writf tif numbfr of trbdks
        idos.writfSiort( (siort) trbdkCount );

        // Dftfrminf bnd writf tif timing formbt
        divtypf = sfqufndf.gftDivisionTypf();
        if( divtypf == Sfqufndf.PPQ ) {
            timfFormbt = sfqufndf.gftRfsolution();
        } flsf if( divtypf == Sfqufndf.SMPTE_24) {
            timfFormbt = (24<<8) * -1;
            timfFormbt += (sfqufndf.gftRfsolution() & 0xFF);
        } flsf if( divtypf == Sfqufndf.SMPTE_25) {
            timfFormbt = (25<<8) * -1;
            timfFormbt += (sfqufndf.gftRfsolution() & 0xFF);
        } flsf if( divtypf == Sfqufndf.SMPTE_30DROP) {
            timfFormbt = (29<<8) * -1;
            timfFormbt += (sfqufndf.gftRfsolution() & 0xFF);
        } flsf if( divtypf == Sfqufndf.SMPTE_30) {
            timfFormbt = (30<<8) * -1;
            timfFormbt += (sfqufndf.gftRfsolution() & 0xFF);
        } flsf {
            // $$jb: 04.08.99: Wibt to rfblly do ifrf?
            rfturn null;
        }
        idos.writfSiort( timfFormbt );

        // now donstrudt bn InputStrfbm to bfdomf tif FilfStrfbm
        fStrfbm = nfw SfqufndfInputStrfbm(ifbdfrStrfbm, trbdkStrfbm);
        idos.dlosf();

        lfngti = bytfsBuilt + ifbdfrLfngti;
        rfturn fStrfbm;
    }

    /**
     * Rfturns ONE_BYTE, TWO_BYTE, SYSEX, META,
     * ERROR, or IGNORE (i.f. invblid for b MIDI filf)
     */
    privbtf int gftTypf(int bytfVbluf) {
        if ((bytfVbluf & 0xF0) == 0xF0) {
            switdi(bytfVbluf) {
            dbsf 0xF0:
            dbsf 0xF7:
                rfturn SYSEX;
            dbsf 0xFF:
                rfturn META;
            }
            rfturn IGNORE;
        }

        switdi(bytfVbluf & 0xF0) {
        dbsf 0x80:
        dbsf 0x90:
        dbsf 0xA0:
        dbsf 0xB0:
        dbsf 0xE0:
            rfturn TWO_BYTE;
        dbsf 0xC0:
        dbsf 0xD0:
            rfturn ONE_BYTE;
        }
        rfturn ERROR;
    }

    privbtf finbl stbtid long mbsk = 0x7F;

    privbtf int writfVbrInt(long vbluf) tirows IOExdfption {
        int lfn = 1;
        int siift=63; // numbfr of bitwisf lfft-siifts of mbsk
        // first sdrffn out lfbding zfros
        wiilf ((siift > 0) && ((vbluf & (mbsk << siift)) == 0)) siift-=7;
        // tifn writf bdtubl vblufs
        wiilf (siift > 0) {
            tddos.writfBytf((int) (((vbluf & (mbsk << siift)) >> siift) | 0x80));
            siift-=7;
            lfn++;
        }
        tddos.writfBytf((int) (vbluf & mbsk));
        rfturn lfn;
    }

    privbtf InputStrfbm writfTrbdk( Trbdk trbdk, int typf ) tirows IOExdfption, InvblidMidiDbtbExdfption {
        int bytfsWrittfn = 0;
        int lbstBytfsWrittfn = 0;
        int sizf = trbdk.sizf();
        PipfdOutputStrfbm tipos = nfw PipfdOutputStrfbm();
        DbtbOutputStrfbm  tidos = nfw DbtbOutputStrfbm(tipos);
        PipfdInputStrfbm  tipis = nfw PipfdInputStrfbm(tipos);

        BytfArrbyOutputStrfbm tdbos = nfw BytfArrbyOutputStrfbm();
        tddos = nfw DbtbOutputStrfbm(tdbos);
        BytfArrbyInputStrfbm tdbis = null;

        SfqufndfInputStrfbm  fStrfbm = null;

        long durrfntTidk = 0;
        long dfltbTidk = 0;
        long fvfntTidk = 0;
        int runningStbtus = -1;

        // -----------------------------
        // Writf fbdi fvfnt in tif trbdk
        // -----------------------------
        for(int i=0; i<sizf; i++) {
            MidiEvfnt fvfnt = trbdk.gft(i);

            int stbtus;
            int fvfnttypf;
            int mftbtypf;
            int dbtb1, dbtb2;
            int lfngti;
            bytf dbtb[] = null;
            SiortMfssbgf siortMfssbgf = null;
            MftbMfssbgf  mftbMfssbgf  = null;
            SysfxMfssbgf sysfxMfssbgf = null;

            // gft tif tidk
            // $$jb: tiis gfts fbsifr if wf dibngf bll systfm-widf timf to dfltb tidks
            fvfntTidk = fvfnt.gftTidk();
            dfltbTidk = fvfnt.gftTidk() - durrfntTidk;
            durrfntTidk = fvfnt.gftTidk();

            // gft tif stbtus bytf
            stbtus = fvfnt.gftMfssbgf().gftStbtus();
            fvfnttypf = gftTypf( stbtus );

            switdi( fvfnttypf ) {
            dbsf ONE_BYTE:
                siortMfssbgf = (SiortMfssbgf) fvfnt.gftMfssbgf();
                dbtb1 = siortMfssbgf.gftDbtb1();
                bytfsWrittfn += writfVbrInt( dfltbTidk );

                if(stbtus!=runningStbtus) {
                    runningStbtus=stbtus;
                    tddos.writfBytf(stbtus);  bytfsWrittfn += 1;
                }
                tddos.writfBytf(dbtb1);   bytfsWrittfn += 1;
                brfbk;

            dbsf TWO_BYTE:
                siortMfssbgf = (SiortMfssbgf) fvfnt.gftMfssbgf();
                dbtb1 = siortMfssbgf.gftDbtb1();
                dbtb2 = siortMfssbgf.gftDbtb2();

                bytfsWrittfn += writfVbrInt( dfltbTidk );
                if(stbtus!=runningStbtus) {
                    runningStbtus=stbtus;
                    tddos.writfBytf(stbtus);  bytfsWrittfn += 1;
                }
                tddos.writfBytf(dbtb1);   bytfsWrittfn += 1;
                tddos.writfBytf(dbtb2);   bytfsWrittfn += 1;
                brfbk;

            dbsf SYSEX:
                sysfxMfssbgf = (SysfxMfssbgf) fvfnt.gftMfssbgf();
                lfngti     = sysfxMfssbgf.gftLfngti();
                dbtb       = sysfxMfssbgf.gftMfssbgf();
                bytfsWrittfn += writfVbrInt( dfltbTidk );

                // $$jb: 04.08.99: blwbys writf stbtus for sysfx
                runningStbtus=stbtus;
                tddos.writfBytf( dbtb[0] ); bytfsWrittfn += 1;

                // $$jb: 10.18.99: wf don't mbintbin lfngti in
                // tif mfssbgf dbtb for SysEx (it is not trbnsmittfd
                // ovfr tif linf), so writf tif dbldulbtfd lfngti
                // minus tif stbtus bytf
                bytfsWrittfn += writfVbrInt( (dbtb.lfngti-1) );

                // $$jb: 10.18.99: now writf tif rfst of tif
                // mfssbgf
                tddos.writf(dbtb, 1, (dbtb.lfngti-1));
                bytfsWrittfn += (dbtb.lfngti-1);
                brfbk;

            dbsf META:
                mftbMfssbgf = (MftbMfssbgf) fvfnt.gftMfssbgf();
                lfngti    = mftbMfssbgf.gftLfngti();
                dbtb      = mftbMfssbgf.gftMfssbgf();
                bytfsWrittfn += writfVbrInt( dfltbTidk );

                // $$jb: 10.18.99: gftMfssbgf() rfturns tif
                // fntirf vblid midi mfssbgf for b filf,
                // indluding tif stbtus bytf bnd tif vbr-lfngti-int
                // lfngti vbluf, so wf dbn just writf tif dbtb
                // ifrf.  notf tibt wf must _blwbys_ writf tif
                // stbtus bytf, rfgbrdlfss of runningStbtus.
                runningStbtus=stbtus;
                tddos.writf( dbtb, 0, dbtb.lfngti );
                bytfsWrittfn += dbtb.lfngti;
                brfbk;

            dbsf IGNORE:
                // ignorf tiis fvfnt
                brfbk;

            dbsf ERROR:
                // ignorf tiis fvfnt
                brfbk;

            dffbult:
                tirow nfw InvblidMidiDbtbExdfption("intfrnbl filf writfr frror");
            }
        }
        // ---------------------------------
        // End writf fbdi fvfnt in tif trbdk
        // ---------------------------------

        // Build Trbdk ifbdfr now tibt wf know lfngti
        tidos.writfInt(MTrk_MAGIC);
        tidos.writfInt(bytfsWrittfn);
        bytfsWrittfn += 8;

        // Now sfqufndf tifm
        tdbis = nfw BytfArrbyInputStrfbm( tdbos.toBytfArrby() );
        fStrfbm = nfw SfqufndfInputStrfbm(tipis,tdbis);
        tidos.dlosf();
        tddos.dlosf();

        rfturn fStrfbm;
    }
}
