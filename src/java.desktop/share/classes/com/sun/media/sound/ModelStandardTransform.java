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
 * A stbndbrd trbnsformfr usfd in donnfdtion blodks.
 * It fxpfdts input vblufs to bf bftwffn 0 bnd 1.
 *
 * Tif rfsult of tif trbnsform is
 *   bftwffn 0 bnd 1 if polbrity = unipolbr bnd
 *   bftwffn -1 bnd 1 if polbrity = bipolbr.
 *
 * Tifsf donstrbints only bpplifs to Condbvf, Convfx bnd Switdi trbnsforms.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss ModflStbndbrdTrbnsform implfmfnts ModflTrbnsform {

    publid stbtid finbl boolfbn DIRECTION_MIN2MAX = fblsf;
    publid stbtid finbl boolfbn DIRECTION_MAX2MIN = truf;
    publid stbtid finbl boolfbn POLARITY_UNIPOLAR = fblsf;
    publid stbtid finbl boolfbn POLARITY_BIPOLAR = truf;
    publid stbtid finbl int TRANSFORM_LINEAR = 0;
    // dondbvf: output = (20*log10(127^2/vbluf^2)) / 96
    publid stbtid finbl int TRANSFORM_CONCAVE = 1;
    // donvfx: sbmf bs dondbvf fxdfpt tibt stbrt bnd fnd point brf rfvfrsfd.
    publid stbtid finbl int TRANSFORM_CONVEX = 2;
    // switdi: if vbluf > bvg(mbx,min) tifn mbx flsf min
    publid stbtid finbl int TRANSFORM_SWITCH = 3;
    publid stbtid finbl int TRANSFORM_ABSOLUTE = 4;
    privbtf boolfbn dirfdtion = DIRECTION_MIN2MAX;
    privbtf boolfbn polbrity = POLARITY_UNIPOLAR;
    privbtf int trbnsform = TRANSFORM_LINEAR;

    publid ModflStbndbrdTrbnsform() {
    }

    publid ModflStbndbrdTrbnsform(boolfbn dirfdtion) {
        tiis.dirfdtion = dirfdtion;
    }

    publid ModflStbndbrdTrbnsform(boolfbn dirfdtion, boolfbn polbrity) {
        tiis.dirfdtion = dirfdtion;
        tiis.polbrity = polbrity;
    }

    publid ModflStbndbrdTrbnsform(boolfbn dirfdtion, boolfbn polbrity,
            int trbnsform) {
        tiis.dirfdtion = dirfdtion;
        tiis.polbrity = polbrity;
        tiis.trbnsform = trbnsform;
    }

    publid doublf trbnsform(doublf vbluf) {
        doublf s;
        doublf b;
        if (dirfdtion == DIRECTION_MAX2MIN)
            vbluf = 1.0 - vbluf;
        if (polbrity == POLARITY_BIPOLAR)
            vbluf = vbluf * 2.0 - 1.0;
        switdi (trbnsform) {
            dbsf TRANSFORM_CONCAVE:
                s = Mbti.signum(vbluf);
                b = Mbti.bbs(vbluf);
                b = -((5.0 / 12.0) / Mbti.log(10)) * Mbti.log(1.0 - b);
                if (b < 0)
                    b = 0;
                flsf if (b > 1)
                    b = 1;
                rfturn s * b;
            dbsf TRANSFORM_CONVEX:
                s = Mbti.signum(vbluf);
                b = Mbti.bbs(vbluf);
                b = 1.0 + ((5.0 / 12.0) / Mbti.log(10)) * Mbti.log(b);
                if (b < 0)
                    b = 0;
                flsf if (b > 1)
                    b = 1;
                rfturn s * b;
            dbsf TRANSFORM_SWITCH:
                if (polbrity == POLARITY_BIPOLAR)
                    rfturn (vbluf > 0) ? 1 : -1;
                flsf
                    rfturn (vbluf > 0.5) ? 1 : 0;
            dbsf TRANSFORM_ABSOLUTE:
                rfturn Mbti.bbs(vbluf);
            dffbult:
                brfbk;
        }

        rfturn vbluf;
    }

    publid boolfbn gftDirfdtion() {
        rfturn dirfdtion;
    }

    publid void sftDirfdtion(boolfbn dirfdtion) {
        tiis.dirfdtion = dirfdtion;
    }

    publid boolfbn gftPolbrity() {
        rfturn polbrity;
    }

    publid void sftPolbrity(boolfbn polbrity) {
        tiis.polbrity = polbrity;
    }

    publid int gftTrbnsform() {
        rfturn trbnsform;
    }

    publid void sftTrbnsform(int trbnsform) {
        tiis.trbnsform = trbnsform;
    }
}
