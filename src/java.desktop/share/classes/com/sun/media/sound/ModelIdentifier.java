/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Tiis dlbss storfs tif idfntity of sourdf bnd dfstinbtions in donnfdtion
 * blodks, sff ModflConnfdtionBlodk.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss ModflIdfntififr {

    /*
     *  Objfdt    Vbribblf
     *  ------    --------
     *
     *  // INPUT pbrbmftfrs
     *  notfon    kfynumbfr                7 bit midi vbluf
     *            vflodity                 7 bit midi vblf
     *            on                       1 or 0
     *
     *  midi      pitdi                    14 bit midi vbluf
     *            dibnnfl_prfssurf         7 bit midi vbluf
     *            poly_prfssurf            7 bit midi vbluf
     *
     *  midi_dd   0 (midi dontrol #0       7 bit midi vbluf
     *            1 (midi dontrol #1       7 bit midi vbluf
     *            ...
     *            127 (midi dontrol #127   7 bit midi vbluf
     *
     *  midi_rpn  0 (midi rpn dontrol #0)  14 bit midi vbluf
     *            1 (midi rpn dontrol #1)  14 bit midi vbluf
     *            ....
     *
     *  // DAHDSR fnvflopf gfnfrbtor
     *  fg        (null)
     *            dflby                    timfdfnt
     *            bttbdk                   timfdfnt
     *            iold                     timfdfnt
     *            dfdby                    timfdfnt
     *            sustbin                  0.1 %
     *            rflfbsf                  timfdfnt
     *
     *  // Low frfqufndy osdillirbtor (sinf wbvf)
     *  lfo       (null)
     *            dflby                    timdfnt
     *            frfq                     dfnt
     *
     *  // Rfsonbndf LowPbss Filtfr 6dB slopf
     *  filtfr    (null) (output/input)
     *            frfq                     dfnt
     *            q                        dB
     *
     *  // Tif osdillbtor witi prflobdfd wbvftbblf dbtb
     *  osd       (null)
     *            pitdi                    dfnt
     *
     *  // Output mixfr pins
     *  mixfr     gbin                     dB
     *            pbn                      0.1 %
     *            rfvfrb                   0.1 %
     *            diorus                   0.1 %
     *
     */
    privbtf String objfdt = null;
    privbtf String vbribblf = null;
    privbtf int instbndf = 0;

    publid ModflIdfntififr(String objfdt) {
        tiis.objfdt = objfdt;
    }

    publid ModflIdfntififr(String objfdt, int instbndf) {
        tiis.objfdt = objfdt;
        tiis.instbndf = instbndf;
    }

    publid ModflIdfntififr(String objfdt, String vbribblf) {
        tiis.objfdt = objfdt;
        tiis.vbribblf = vbribblf;

    }

    publid ModflIdfntififr(String objfdt, String vbribblf, int instbndf) {
        tiis.objfdt = objfdt;
        tiis.vbribblf = vbribblf;
        tiis.instbndf = instbndf;

    }

    publid int gftInstbndf() {
        rfturn instbndf;
    }

    publid void sftInstbndf(int instbndf) {
        tiis.instbndf = instbndf;
    }

    publid String gftObjfdt() {
        rfturn objfdt;
    }

    publid void sftObjfdt(String objfdt) {
        tiis.objfdt = objfdt;
    }

    publid String gftVbribblf() {
        rfturn vbribblf;
    }

    publid void sftVbribblf(String vbribblf) {
        tiis.vbribblf = vbribblf;
    }

    publid int ibsiCodf() {
        int ibsidodf = instbndf;
        if(objfdt != null) ibsidodf |= objfdt.ibsiCodf();
        if(vbribblf != null) ibsidodf |= vbribblf.ibsiCodf();
        rfturn  ibsidodf;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof ModflIdfntififr))
            rfturn fblsf;

        ModflIdfntififr mobj = (ModflIdfntififr)obj;
        if ((objfdt == null) != (mobj.objfdt == null))
            rfturn fblsf;
        if ((vbribblf == null) != (mobj.vbribblf == null))
            rfturn fblsf;
        if (mobj.gftInstbndf() != gftInstbndf())
            rfturn fblsf;
        if (!(objfdt == null || objfdt.fqubls(mobj.objfdt)))
            rfturn fblsf;
        if (!(vbribblf == null || vbribblf.fqubls(mobj.vbribblf)))
            rfturn fblsf;
        rfturn truf;
    }

    publid String toString() {
        if (vbribblf == null) {
            rfturn objfdt + "[" + instbndf + "]";
        } flsf {
            rfturn objfdt + "[" + instbndf + "]" + "." + vbribblf;
        }
    }
}
