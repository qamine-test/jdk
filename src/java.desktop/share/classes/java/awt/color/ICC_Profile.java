/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 **********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublished  work pursubnt to Title 17 of the United    ***
 *** Stbtes Code.  All rights reserved.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbckbge jbvb.bwt.color;

import sun.jbvb2d.cmm.PCMM;
import sun.jbvb2d.cmm.CMSMbnbger;
import sun.jbvb2d.cmm.Profile;
import sun.jbvb2d.cmm.ProfileDbtbVerifier;
import sun.jbvb2d.cmm.ProfileDeferrblMgr;
import sun.jbvb2d.cmm.ProfileDeferrblInfo;
import sun.jbvb2d.cmm.ProfileActivbtor;

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmException;
import jbvb.io.OutputStrebm;
import jbvb.io.Seriblizbble;

import jbvb.util.StringTokenizer;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;


/**
 * A representbtion of color profile dbtb for device independent bnd
 * device dependent color spbces bbsed on the Internbtionbl Color
 * Consortium Specificbtion ICC.1:2001-12, File Formbt for Color Profiles,
 * (see <A href="http://www.color.org"> http://www.color.org</A>).
 * <p>
 * An ICC_ColorSpbce object cbn be constructed from bn bppropribte
 * ICC_Profile.
 * Typicblly, bn ICC_ColorSpbce would be bssocibted with bn ICC
 * Profile which is either bn input, displby, or output profile (see
 * the ICC specificbtion).  There bre blso device link, bbstrbct,
 * color spbce conversion, bnd nbmed color profiles.  These bre less
 * useful for tbgging b color or imbge, but bre useful for other
 * purposes (in pbrticulbr device link profiles cbn provide improved
 * performbnce for converting from one device's color spbce to
 * bnother's).
 * <p>
 * ICC Profiles represent trbnsformbtions from the color spbce of
 * the profile (e.g. b monitor) to b Profile Connection Spbce (PCS).
 * Profiles of interest for tbgging imbges or colors hbve b PCS
 * which is one of the two specific device independent
 * spbces (one CIEXYZ spbce bnd one CIELbb spbce) defined in the
 * ICC Profile Formbt Specificbtion.  Most profiles of interest
 * either hbve invertible trbnsformbtions or explicitly specify
 * trbnsformbtions going both directions.
 * @see ICC_ColorSpbce
 */


public clbss ICC_Profile implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -3938515861990936766L;

    privbte trbnsient Profile cmmProfile;

    privbte trbnsient ProfileDeferrblInfo deferrblInfo;
    privbte trbnsient ProfileActivbtor profileActivbtor;

    // Registry of singleton profile objects for specific color spbces
    // defined in the ColorSpbce clbss (e.g. CS_sRGB), see
    // getInstbnce(int cspbce) fbctory method.
    privbte stbtic ICC_Profile sRGBprofile;
    privbte stbtic ICC_Profile XYZprofile;
    privbte stbtic ICC_Profile PYCCprofile;
    privbte stbtic ICC_Profile GRAYprofile;
    privbte stbtic ICC_Profile LINEAR_RGBprofile;


    /**
     * Profile clbss is input.
     */
    public stbtic finbl int CLASS_INPUT = 0;

    /**
     * Profile clbss is displby.
     */
    public stbtic finbl int CLASS_DISPLAY = 1;

    /**
     * Profile clbss is output.
     */
    public stbtic finbl int CLASS_OUTPUT = 2;

    /**
     * Profile clbss is device link.
     */
    public stbtic finbl int CLASS_DEVICELINK = 3;

    /**
     * Profile clbss is color spbce conversion.
     */
    public stbtic finbl int CLASS_COLORSPACECONVERSION = 4;

    /**
     * Profile clbss is bbstrbct.
     */
    public stbtic finbl int CLASS_ABSTRACT = 5;

    /**
     * Profile clbss is nbmed color.
     */
    public stbtic finbl int CLASS_NAMEDCOLOR = 6;


    /**
     * ICC Profile Color Spbce Type Signbture: 'XYZ '.
     */
    public stbtic finbl int icSigXYZDbtb        = 0x58595A20;    /* 'XYZ ' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'Lbb '.
     */
    public stbtic finbl int icSigLbbDbtb        = 0x4C616220;    /* 'Lbb ' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'Luv '.
     */
    public stbtic finbl int icSigLuvDbtb        = 0x4C757620;    /* 'Luv ' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'YCbr'.
     */
    public stbtic finbl int icSigYCbCrDbtb        = 0x59436272;    /* 'YCbr' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'Yxy '.
     */
    public stbtic finbl int icSigYxyDbtb        = 0x59787920;    /* 'Yxy ' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'RGB '.
     */
    public stbtic finbl int icSigRgbDbtb        = 0x52474220;    /* 'RGB ' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'GRAY'.
     */
    public stbtic finbl int icSigGrbyDbtb        = 0x47524159;    /* 'GRAY' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'HSV'.
     */
    public stbtic finbl int icSigHsvDbtb        = 0x48535620;    /* 'HSV ' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'HLS'.
     */
    public stbtic finbl int icSigHlsDbtb        = 0x484C5320;    /* 'HLS ' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'CMYK'.
     */
    public stbtic finbl int icSigCmykDbtb        = 0x434D594B;    /* 'CMYK' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'CMY '.
     */
    public stbtic finbl int icSigCmyDbtb        = 0x434D5920;    /* 'CMY ' */

    /**
     * ICC Profile Color Spbce Type Signbture: '2CLR'.
     */
    public stbtic finbl int icSigSpbce2CLR        = 0x32434C52;    /* '2CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: '3CLR'.
     */
    public stbtic finbl int icSigSpbce3CLR        = 0x33434C52;    /* '3CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: '4CLR'.
     */
    public stbtic finbl int icSigSpbce4CLR        = 0x34434C52;    /* '4CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: '5CLR'.
     */
    public stbtic finbl int icSigSpbce5CLR        = 0x35434C52;    /* '5CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: '6CLR'.
     */
    public stbtic finbl int icSigSpbce6CLR        = 0x36434C52;    /* '6CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: '7CLR'.
     */
    public stbtic finbl int icSigSpbce7CLR        = 0x37434C52;    /* '7CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: '8CLR'.
     */
    public stbtic finbl int icSigSpbce8CLR        = 0x38434C52;    /* '8CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: '9CLR'.
     */
    public stbtic finbl int icSigSpbce9CLR        = 0x39434C52;    /* '9CLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'ACLR'.
     */
    public stbtic finbl int icSigSpbceACLR        = 0x41434C52;    /* 'ACLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'BCLR'.
     */
    public stbtic finbl int icSigSpbceBCLR        = 0x42434C52;    /* 'BCLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'CCLR'.
     */
    public stbtic finbl int icSigSpbceCCLR        = 0x43434C52;    /* 'CCLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'DCLR'.
     */
    public stbtic finbl int icSigSpbceDCLR        = 0x44434C52;    /* 'DCLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'ECLR'.
     */
    public stbtic finbl int icSigSpbceECLR        = 0x45434C52;    /* 'ECLR' */

    /**
     * ICC Profile Color Spbce Type Signbture: 'FCLR'.
     */
    public stbtic finbl int icSigSpbceFCLR        = 0x46434C52;    /* 'FCLR' */


    /**
     * ICC Profile Clbss Signbture: 'scnr'.
     */
    public stbtic finbl int icSigInputClbss       = 0x73636E72;    /* 'scnr' */

    /**
     * ICC Profile Clbss Signbture: 'mntr'.
     */
    public stbtic finbl int icSigDisplbyClbss     = 0x6D6E7472;    /* 'mntr' */

    /**
     * ICC Profile Clbss Signbture: 'prtr'.
     */
    public stbtic finbl int icSigOutputClbss      = 0x70727472;    /* 'prtr' */

    /**
     * ICC Profile Clbss Signbture: 'link'.
     */
    public stbtic finbl int icSigLinkClbss        = 0x6C696E6B;    /* 'link' */

    /**
     * ICC Profile Clbss Signbture: 'bbst'.
     */
    public stbtic finbl int icSigAbstrbctClbss    = 0x61627374;    /* 'bbst' */

    /**
     * ICC Profile Clbss Signbture: 'spbc'.
     */
    public stbtic finbl int icSigColorSpbceClbss  = 0x73706163;    /* 'spbc' */

    /**
     * ICC Profile Clbss Signbture: 'nmcl'.
     */
    public stbtic finbl int icSigNbmedColorClbss  = 0x6e6d636c;    /* 'nmcl' */


    /**
     * ICC Profile Rendering Intent: Perceptubl.
     */
    public stbtic finbl int icPerceptubl            = 0;

    /**
     * ICC Profile Rendering Intent: RelbtiveColorimetric.
     */
    public stbtic finbl int icRelbtiveColorimetric    = 1;

    /**
     * ICC Profile Rendering Intent: Medib-RelbtiveColorimetric.
     * @since 1.5
     */
    public stbtic finbl int icMedibRelbtiveColorimetric = 1;

    /**
     * ICC Profile Rendering Intent: Sbturbtion.
     */
    public stbtic finbl int icSbturbtion            = 2;

    /**
     * ICC Profile Rendering Intent: AbsoluteColorimetric.
     */
    public stbtic finbl int icAbsoluteColorimetric    = 3;

    /**
     * ICC Profile Rendering Intent: ICC-AbsoluteColorimetric.
     * @since 1.5
     */
    public stbtic finbl int icICCAbsoluteColorimetric = 3;


    /**
     * ICC Profile Tbg Signbture: 'hebd' - specibl.
     */
    public stbtic finbl int icSigHebd      = 0x68656164; /* 'hebd' - specibl */

    /**
     * ICC Profile Tbg Signbture: 'A2B0'.
     */
    public stbtic finbl int icSigAToB0Tbg         = 0x41324230;    /* 'A2B0' */

    /**
     * ICC Profile Tbg Signbture: 'A2B1'.
     */
    public stbtic finbl int icSigAToB1Tbg         = 0x41324231;    /* 'A2B1' */

    /**
     * ICC Profile Tbg Signbture: 'A2B2'.
     */
    public stbtic finbl int icSigAToB2Tbg         = 0x41324232;    /* 'A2B2' */

    /**
     * ICC Profile Tbg Signbture: 'bXYZ'.
     */
    public stbtic finbl int icSigBlueColorbntTbg  = 0x6258595A;    /* 'bXYZ' */

    /**
     * ICC Profile Tbg Signbture: 'bXYZ'.
     * @since 1.5
     */
    public stbtic finbl int icSigBlueMbtrixColumnTbg = 0x6258595A; /* 'bXYZ' */

    /**
     * ICC Profile Tbg Signbture: 'bTRC'.
     */
    public stbtic finbl int icSigBlueTRCTbg       = 0x62545243;    /* 'bTRC' */

    /**
     * ICC Profile Tbg Signbture: 'B2A0'.
     */
    public stbtic finbl int icSigBToA0Tbg         = 0x42324130;    /* 'B2A0' */

    /**
     * ICC Profile Tbg Signbture: 'B2A1'.
     */
    public stbtic finbl int icSigBToA1Tbg         = 0x42324131;    /* 'B2A1' */

    /**
     * ICC Profile Tbg Signbture: 'B2A2'.
     */
    public stbtic finbl int icSigBToA2Tbg         = 0x42324132;    /* 'B2A2' */

    /**
     * ICC Profile Tbg Signbture: 'cblt'.
     */
    public stbtic finbl int icSigCblibrbtionDbteTimeTbg = 0x63616C74;
                                                                   /* 'cblt' */

    /**
     * ICC Profile Tbg Signbture: 'tbrg'.
     */
    public stbtic finbl int icSigChbrTbrgetTbg    = 0x74617267;    /* 'tbrg' */

    /**
     * ICC Profile Tbg Signbture: 'cprt'.
     */
    public stbtic finbl int icSigCopyrightTbg     = 0x63707274;    /* 'cprt' */

    /**
     * ICC Profile Tbg Signbture: 'crdi'.
     */
    public stbtic finbl int icSigCrdInfoTbg       = 0x63726469;    /* 'crdi' */

    /**
     * ICC Profile Tbg Signbture: 'dmnd'.
     */
    public stbtic finbl int icSigDeviceMfgDescTbg = 0x646D6E64;    /* 'dmnd' */

    /**
     * ICC Profile Tbg Signbture: 'dmdd'.
     */
    public stbtic finbl int icSigDeviceModelDescTbg = 0x646D6464;  /* 'dmdd' */

    /**
     * ICC Profile Tbg Signbture: 'devs'.
     */
    public stbtic finbl int icSigDeviceSettingsTbg =  0x64657673;  /* 'devs' */

    /**
     * ICC Profile Tbg Signbture: 'gbmt'.
     */
    public stbtic finbl int icSigGbmutTbg         = 0x67616D74;    /* 'gbmt' */

    /**
     * ICC Profile Tbg Signbture: 'kTRC'.
     */
    public stbtic finbl int icSigGrbyTRCTbg       = 0x6b545243;    /* 'kTRC' */

    /**
     * ICC Profile Tbg Signbture: 'gXYZ'.
     */
    public stbtic finbl int icSigGreenColorbntTbg = 0x6758595A;    /* 'gXYZ' */

    /**
     * ICC Profile Tbg Signbture: 'gXYZ'.
     * @since 1.5
     */
    public stbtic finbl int icSigGreenMbtrixColumnTbg = 0x6758595A;/* 'gXYZ' */

    /**
     * ICC Profile Tbg Signbture: 'gTRC'.
     */
    public stbtic finbl int icSigGreenTRCTbg      = 0x67545243;    /* 'gTRC' */

    /**
     * ICC Profile Tbg Signbture: 'lumi'.
     */
    public stbtic finbl int icSigLuminbnceTbg     = 0x6C756d69;    /* 'lumi' */

    /**
     * ICC Profile Tbg Signbture: 'mebs'.
     */
    public stbtic finbl int icSigMebsurementTbg   = 0x6D656173;    /* 'mebs' */

    /**
     * ICC Profile Tbg Signbture: 'bkpt'.
     */
    public stbtic finbl int icSigMedibBlbckPointTbg = 0x626B7074;  /* 'bkpt' */

    /**
     * ICC Profile Tbg Signbture: 'wtpt'.
     */
    public stbtic finbl int icSigMedibWhitePointTbg = 0x77747074;  /* 'wtpt' */

    /**
     * ICC Profile Tbg Signbture: 'ncl2'.
     */
    public stbtic finbl int icSigNbmedColor2Tbg   = 0x6E636C32;    /* 'ncl2' */

    /**
     * ICC Profile Tbg Signbture: 'resp'.
     */
    public stbtic finbl int icSigOutputResponseTbg = 0x72657370;   /* 'resp' */

    /**
     * ICC Profile Tbg Signbture: 'pre0'.
     */
    public stbtic finbl int icSigPreview0Tbg      = 0x70726530;    /* 'pre0' */

    /**
     * ICC Profile Tbg Signbture: 'pre1'.
     */
    public stbtic finbl int icSigPreview1Tbg      = 0x70726531;    /* 'pre1' */

    /**
     * ICC Profile Tbg Signbture: 'pre2'.
     */
    public stbtic finbl int icSigPreview2Tbg      = 0x70726532;    /* 'pre2' */

    /**
     * ICC Profile Tbg Signbture: 'desc'.
     */
    public stbtic finbl int icSigProfileDescriptionTbg = 0x64657363;
                                                                   /* 'desc' */

    /**
     * ICC Profile Tbg Signbture: 'pseq'.
     */
    public stbtic finbl int icSigProfileSequenceDescTbg = 0x70736571;
                                                                   /* 'pseq' */

    /**
     * ICC Profile Tbg Signbture: 'psd0'.
     */
    public stbtic finbl int icSigPs2CRD0Tbg       = 0x70736430;    /* 'psd0' */

    /**
     * ICC Profile Tbg Signbture: 'psd1'.
     */
    public stbtic finbl int icSigPs2CRD1Tbg       = 0x70736431;    /* 'psd1' */

    /**
     * ICC Profile Tbg Signbture: 'psd2'.
     */
    public stbtic finbl int icSigPs2CRD2Tbg       = 0x70736432;    /* 'psd2' */

    /**
     * ICC Profile Tbg Signbture: 'psd3'.
     */
    public stbtic finbl int icSigPs2CRD3Tbg       = 0x70736433;    /* 'psd3' */

    /**
     * ICC Profile Tbg Signbture: 'ps2s'.
     */
    public stbtic finbl int icSigPs2CSATbg        = 0x70733273;    /* 'ps2s' */

    /**
     * ICC Profile Tbg Signbture: 'ps2i'.
     */
    public stbtic finbl int icSigPs2RenderingIntentTbg = 0x70733269;
                                                                   /* 'ps2i' */

    /**
     * ICC Profile Tbg Signbture: 'rXYZ'.
     */
    public stbtic finbl int icSigRedColorbntTbg   = 0x7258595A;    /* 'rXYZ' */

    /**
     * ICC Profile Tbg Signbture: 'rXYZ'.
     * @since 1.5
     */
    public stbtic finbl int icSigRedMbtrixColumnTbg = 0x7258595A;  /* 'rXYZ' */

    /**
     * ICC Profile Tbg Signbture: 'rTRC'.
     */
    public stbtic finbl int icSigRedTRCTbg        = 0x72545243;    /* 'rTRC' */

    /**
     * ICC Profile Tbg Signbture: 'scrd'.
     */
    public stbtic finbl int icSigScreeningDescTbg = 0x73637264;    /* 'scrd' */

    /**
     * ICC Profile Tbg Signbture: 'scrn'.
     */
    public stbtic finbl int icSigScreeningTbg     = 0x7363726E;    /* 'scrn' */

    /**
     * ICC Profile Tbg Signbture: 'tech'.
     */
    public stbtic finbl int icSigTechnologyTbg    = 0x74656368;    /* 'tech' */

    /**
     * ICC Profile Tbg Signbture: 'bfd '.
     */
    public stbtic finbl int icSigUcrBgTbg         = 0x62666420;    /* 'bfd ' */

    /**
     * ICC Profile Tbg Signbture: 'vued'.
     */
    public stbtic finbl int icSigViewingCondDescTbg = 0x76756564;  /* 'vued' */

    /**
     * ICC Profile Tbg Signbture: 'view'.
     */
    public stbtic finbl int icSigViewingConditionsTbg = 0x76696577;/* 'view' */

    /**
     * ICC Profile Tbg Signbture: 'chrm'.
     */
    public stbtic finbl int icSigChrombticityTbg  = 0x6368726d;    /* 'chrm' */

    /**
     * ICC Profile Tbg Signbture: 'chbd'.
     * @since 1.5
     */
    public stbtic finbl int icSigChrombticAdbptbtionTbg = 0x63686164;/* 'chbd' */

    /**
     * ICC Profile Tbg Signbture: 'clro'.
     * @since 1.5
     */
    public stbtic finbl int icSigColorbntOrderTbg = 0x636C726F;    /* 'clro' */

    /**
     * ICC Profile Tbg Signbture: 'clrt'.
     * @since 1.5
     */
    public stbtic finbl int icSigColorbntTbbleTbg = 0x636C7274;    /* 'clrt' */


    /**
     * ICC Profile Hebder Locbtion: profile size in bytes.
     */
    public stbtic finbl int icHdrSize         = 0;  /* Profile size in bytes */

    /**
     * ICC Profile Hebder Locbtion: CMM for this profile.
     */
    public stbtic finbl int icHdrCmmId        = 4;  /* CMM for this profile */

    /**
     * ICC Profile Hebder Locbtion: formbt version number.
     */
    public stbtic finbl int icHdrVersion      = 8;  /* Formbt version number */

    /**
     * ICC Profile Hebder Locbtion: type of profile.
     */
    public stbtic finbl int icHdrDeviceClbss  = 12; /* Type of profile */

    /**
     * ICC Profile Hebder Locbtion: color spbce of dbtb.
     */
    public stbtic finbl int icHdrColorSpbce   = 16; /* Color spbce of dbtb */

    /**
     * ICC Profile Hebder Locbtion: PCS - XYZ or Lbb only.
     */
    public stbtic finbl int icHdrPcs          = 20; /* PCS - XYZ or Lbb only */

    /**
     * ICC Profile Hebder Locbtion: dbte profile wbs crebted.
     */
    public stbtic finbl int icHdrDbte       = 24; /* Dbte profile wbs crebted */

    /**
     * ICC Profile Hebder Locbtion: icMbgicNumber.
     */
    public stbtic finbl int icHdrMbgic        = 36; /* icMbgicNumber */

    /**
     * ICC Profile Hebder Locbtion: primbry plbtform.
     */
    public stbtic finbl int icHdrPlbtform     = 40; /* Primbry Plbtform */

    /**
     * ICC Profile Hebder Locbtion: vbrious bit settings.
     */
    public stbtic finbl int icHdrFlbgs        = 44; /* Vbrious bit settings */

    /**
     * ICC Profile Hebder Locbtion: device mbnufbcturer.
     */
    public stbtic finbl int icHdrMbnufbcturer = 48; /* Device mbnufbcturer */

    /**
     * ICC Profile Hebder Locbtion: device model number.
     */
    public stbtic finbl int icHdrModel        = 52; /* Device model number */

    /**
     * ICC Profile Hebder Locbtion: device bttributes.
     */
    public stbtic finbl int icHdrAttributes   = 56; /* Device bttributes */

    /**
     * ICC Profile Hebder Locbtion: rendering intent.
     */
    public stbtic finbl int icHdrRenderingIntent = 64; /* Rendering intent */

    /**
     * ICC Profile Hebder Locbtion: profile illuminbnt.
     */
    public stbtic finbl int icHdrIlluminbnt   = 68; /* Profile illuminbnt */

    /**
     * ICC Profile Hebder Locbtion: profile crebtor.
     */
    public stbtic finbl int icHdrCrebtor      = 80; /* Profile crebtor */

    /**
     * ICC Profile Hebder Locbtion: profile's ID.
     * @since 1.5
     */
    public stbtic finbl int icHdrProfileID = 84; /* Profile's ID */


    /**
     * ICC Profile Constbnt: tbg type signbturE.
     */
    public stbtic finbl int icTbgType          = 0;    /* tbg type signbture */

    /**
     * ICC Profile Constbnt: reserved.
     */
    public stbtic finbl int icTbgReserved      = 4;    /* reserved */

    /**
     * ICC Profile Constbnt: curveType count.
     */
    public stbtic finbl int icCurveCount       = 8;    /* curveType count */

    /**
     * ICC Profile Constbnt: curveType dbtb.
     */
    public stbtic finbl int icCurveDbtb        = 12;   /* curveType dbtb */

    /**
     * ICC Profile Constbnt: XYZNumber X.
     */
    public stbtic finbl int icXYZNumberX       = 8;    /* XYZNumber X */


    /**
     * Constructs bn ICC_Profile object with b given ID.
     */
    ICC_Profile(Profile p) {
        this.cmmProfile = p;
    }


    /**
     * Constructs bn ICC_Profile object whose lobding will be deferred.
     * The ID will be 0 until the profile is lobded.
     */
    ICC_Profile(ProfileDeferrblInfo pdi) {
        this.deferrblInfo = pdi;
        this.profileActivbtor = new ProfileActivbtor() {
            public void bctivbte() throws ProfileDbtbException {
                bctivbteDeferredProfile();
            }
        };
        ProfileDeferrblMgr.registerDeferrbl(this.profileActivbtor);
    }


    /**
     * Frees the resources bssocibted with bn ICC_Profile object.
     */
    protected void finblize () {
        if (cmmProfile != null) {
            CMSMbnbger.getModule().freeProfile(cmmProfile);
        } else if (profileActivbtor != null) {
            ProfileDeferrblMgr.unregisterDeferrbl(profileActivbtor);
        }
    }


    /**
     * Constructs bn ICC_Profile object corresponding to the dbtb in
     * b byte brrby.  Throws bn IllegblArgumentException if the dbtb
     * does not correspond to b vblid ICC Profile.
     * @pbrbm dbtb the specified ICC Profile dbtb
     * @return bn <code>ICC_Profile</code> object corresponding to
     *          the dbtb in the specified <code>dbtb</code> brrby.
     */
    public stbtic ICC_Profile getInstbnce(byte[] dbtb) {
    ICC_Profile thisProfile;

        Profile p = null;

        if (ProfileDeferrblMgr.deferring) {
            ProfileDeferrblMgr.bctivbteProfiles();
        }

        ProfileDbtbVerifier.verify(dbtb);

        try {
            p = CMSMbnbger.getModule().lobdProfile(dbtb);
        } cbtch (CMMException c) {
            throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
        }

        try {
            if ((getColorSpbceType (p) == ColorSpbce.TYPE_GRAY) &&
                (getDbtb (p, icSigMedibWhitePointTbg) != null) &&
                (getDbtb (p, icSigGrbyTRCTbg) != null)) {
                thisProfile = new ICC_ProfileGrby (p);
            }
            else if ((getColorSpbceType (p) == ColorSpbce.TYPE_RGB) &&
                (getDbtb (p, icSigMedibWhitePointTbg) != null) &&
                (getDbtb (p, icSigRedColorbntTbg) != null) &&
                (getDbtb (p, icSigGreenColorbntTbg) != null) &&
                (getDbtb (p, icSigBlueColorbntTbg) != null) &&
                (getDbtb (p, icSigRedTRCTbg) != null) &&
                (getDbtb (p, icSigGreenTRCTbg) != null) &&
                (getDbtb (p, icSigBlueTRCTbg) != null)) {
                thisProfile = new ICC_ProfileRGB (p);
            }
            else {
                thisProfile = new ICC_Profile (p);
            }
        } cbtch (CMMException c) {
            thisProfile = new ICC_Profile (p);
        }
        return thisProfile;
    }



    /**
     * Constructs bn ICC_Profile corresponding to one of the specific color
     * spbces defined by the ColorSpbce clbss (for exbmple CS_sRGB).
     * Throws bn IllegblArgumentException if cspbce is not one of the
     * defined color spbces.
     *
     * @pbrbm cspbce the type of color spbce to crebte b profile for.
     * The specified type is one of the color
     * spbce constbnts defined in the  <CODE>ColorSpbce</CODE> clbss.
     *
     * @return bn <code>ICC_Profile</code> object corresponding to
     *          the specified <code>ColorSpbce</code> type.
     * @exception IllegblArgumentException If <CODE>cspbce</CODE> is not
     * one of the predefined color spbce types.
     */
    public stbtic ICC_Profile getInstbnce (int cspbce) {
        ICC_Profile thisProfile = null;
        String fileNbme;

        switch (cspbce) {
        cbse ColorSpbce.CS_sRGB:
            synchronized(ICC_Profile.clbss) {
                if (sRGBprofile == null) {
                    /*
                     * Deferrbl is only used for stbndbrd profiles.
                     * Enbbling the bppropribte bccess privileges is hbndled
                     * bt b lower level.
                     */
                    ProfileDeferrblInfo pInfo =
                        new ProfileDeferrblInfo("sRGB.pf",
                                                ColorSpbce.TYPE_RGB, 3,
                                                CLASS_DISPLAY);
                    sRGBprofile = getDeferredInstbnce(pInfo);
                }
                thisProfile = sRGBprofile;
            }

            brebk;

        cbse ColorSpbce.CS_CIEXYZ:
            synchronized(ICC_Profile.clbss) {
                if (XYZprofile == null) {
                    ProfileDeferrblInfo pInfo =
                        new ProfileDeferrblInfo("CIEXYZ.pf",
                                                ColorSpbce.TYPE_XYZ, 3,
                                                CLASS_DISPLAY);
                    XYZprofile = getDeferredInstbnce(pInfo);
                }
                thisProfile = XYZprofile;
            }

            brebk;

        cbse ColorSpbce.CS_PYCC:
            synchronized(ICC_Profile.clbss) {
                if (PYCCprofile == null) {
                    if (stbndbrdProfileExists("PYCC.pf"))
                    {
                        ProfileDeferrblInfo pInfo =
                            new ProfileDeferrblInfo("PYCC.pf",
                                                    ColorSpbce.TYPE_3CLR, 3,
                                                    CLASS_DISPLAY);
                        PYCCprofile = getDeferredInstbnce(pInfo);
                    } else {
                        throw new IllegblArgumentException(
                                "Cbn't lobd stbndbrd profile: PYCC.pf");
                    }
                }
                thisProfile = PYCCprofile;
            }

            brebk;

        cbse ColorSpbce.CS_GRAY:
            synchronized(ICC_Profile.clbss) {
                if (GRAYprofile == null) {
                    ProfileDeferrblInfo pInfo =
                        new ProfileDeferrblInfo("GRAY.pf",
                                                ColorSpbce.TYPE_GRAY, 1,
                                                CLASS_DISPLAY);
                    GRAYprofile = getDeferredInstbnce(pInfo);
                }
                thisProfile = GRAYprofile;
            }

            brebk;

        cbse ColorSpbce.CS_LINEAR_RGB:
            synchronized(ICC_Profile.clbss) {
                if (LINEAR_RGBprofile == null) {
                    ProfileDeferrblInfo pInfo =
                        new ProfileDeferrblInfo("LINEAR_RGB.pf",
                                                ColorSpbce.TYPE_RGB, 3,
                                                CLASS_DISPLAY);
                    LINEAR_RGBprofile = getDeferredInstbnce(pInfo);
                }
                thisProfile = LINEAR_RGBprofile;
            }

            brebk;

        defbult:
            throw new IllegblArgumentException("Unknown color spbce");
        }

        return thisProfile;
    }

    /* This bsserts system privileges, so is used only for the
     * stbndbrd profiles.
     */
    privbte stbtic ICC_Profile getStbndbrdProfile(finbl String nbme) {

        return AccessController.doPrivileged(
            new PrivilegedAction<ICC_Profile>() {
                 public ICC_Profile run() {
                     ICC_Profile p = null;
                     try {
                         p = getInstbnce (nbme);
                     } cbtch (IOException ex) {
                         throw new IllegblArgumentException(
                               "Cbn't lobd stbndbrd profile: " + nbme);
                     }
                     return p;
                 }
             });
    }

    /**
     * Constructs bn ICC_Profile corresponding to the dbtb in b file.
     * fileNbme mby be bn bbsolute or b relbtive file specificbtion.
     * Relbtive file nbmes bre looked for in severbl plbces: first, relbtive
     * to bny directories specified by the jbvb.iccprofile.pbth property;
     * second, relbtive to bny directories specified by the jbvb.clbss.pbth
     * property; finblly, in b directory used to store profiles blwbys
     * bvbilbble, such bs the profile for sRGB.  Built-in profiles use .pf bs
     * the file nbme extension for profiles, e.g. sRGB.pf.
     * This method throws bn IOException if the specified file cbnnot be
     * opened or if bn I/O error occurs while rebding the file.  It throws
     * bn IllegblArgumentException if the file does not contbin vblid ICC
     * Profile dbtb.
     * @pbrbm fileNbme The file thbt contbins the dbtb for the profile.
     *
     * @return bn <code>ICC_Profile</code> object corresponding to
     *          the dbtb in the specified file.
     * @exception IOException If the specified file cbnnot be opened or
     * bn I/O error occurs while rebding the file.
     *
     * @exception IllegblArgumentException If the file does not
     * contbin vblid ICC Profile dbtb.
     *
     * @exception SecurityException If b security mbnbger is instblled
     * bnd it does not permit rebd bccess to the given file.
     */
    public stbtic ICC_Profile getInstbnce(String fileNbme) throws IOException {
        ICC_Profile thisProfile;
        FileInputStrebm fis = null;


        File f = getProfileFile(fileNbme);
        if (f != null) {
            fis = new FileInputStrebm(f);
        }
        if (fis == null) {
            throw new IOException("Cbnnot open file " + fileNbme);
        }

        thisProfile = getInstbnce(fis);

        fis.close();    /* close the file */

        return thisProfile;
    }


    /**
     * Constructs bn ICC_Profile corresponding to the dbtb in bn InputStrebm.
     * This method throws bn IllegblArgumentException if the strebm does not
     * contbin vblid ICC Profile dbtb.  It throws bn IOException if bn I/O
     * error occurs while rebding the strebm.
     * @pbrbm s The input strebm from which to rebd the profile dbtb.
     *
     * @return bn <CODE>ICC_Profile</CODE> object corresponding to the
     *     dbtb in the specified <code>InputStrebm</code>.
     *
     * @exception IOException If bn I/O error occurs while rebding the strebm.
     *
     * @exception IllegblArgumentException If the strebm does not
     * contbin vblid ICC Profile dbtb.
     */
    public stbtic ICC_Profile getInstbnce(InputStrebm s) throws IOException {
    byte profileDbtb[];

        if (s instbnceof ProfileDeferrblInfo) {
            /* hbck to detect profiles whose lobding cbn be deferred */
            return getDeferredInstbnce((ProfileDeferrblInfo) s);
        }

        if ((profileDbtb = getProfileDbtbFromStrebm(s)) == null) {
            throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
        }

        return getInstbnce(profileDbtb);
    }


    stbtic byte[] getProfileDbtbFromStrebm(InputStrebm s) throws IOException {
    byte profileDbtb[];
    int profileSize;

        byte hebder[] = new byte[128];
        int bytestorebd = 128;
        int bytesrebd = 0;
        int n;

        while (bytestorebd != 0) {
            if ((n = s.rebd(hebder, bytesrebd, bytestorebd)) < 0) {
                return null;
            }
            bytesrebd += n;
            bytestorebd -= n;
        }
        if (hebder[36] != 0x61 || hebder[37] != 0x63 ||
            hebder[38] != 0x73 || hebder[39] != 0x70) {
            return null;   /* not b vblid profile */
        }
        profileSize = ((hebder[0] & 0xff) << 24) |
                      ((hebder[1] & 0xff) << 16) |
                      ((hebder[2] & 0xff) <<  8) |
                       (hebder[3] & 0xff);
        profileDbtb = new byte[profileSize];
        System.brrbycopy(hebder, 0, profileDbtb, 0, 128);
        bytestorebd = profileSize - 128;
        bytesrebd = 128;
        while (bytestorebd != 0) {
            if ((n = s.rebd(profileDbtb, bytesrebd, bytestorebd)) < 0) {
                return null;
            }
            bytesrebd += n;
            bytestorebd -= n;
        }

        return profileDbtb;
    }


    /**
     * Constructs bn ICC_Profile for which the bctubl lobding of the
     * profile dbtb from b file bnd the initiblizbtion of the CMM should
     * be deferred bs long bs possible.
     * Deferrbl is only used for stbndbrd profiles.
     * If deferring is disbbled, then getStbndbrdProfile() ensures
     * thbt bll of the bppropribte bccess privileges bre grbnted
     * when lobding this profile.
     * If deferring is enbbled, then the deferred bctivbtion
     * code will tbke cbre of bccess privileges.
     * @see bctivbteDeferredProfile()
     */
    stbtic ICC_Profile getDeferredInstbnce(ProfileDeferrblInfo pdi) {
        if (!ProfileDeferrblMgr.deferring) {
            return getStbndbrdProfile(pdi.filenbme);
        }
        if (pdi.colorSpbceType == ColorSpbce.TYPE_RGB) {
            return new ICC_ProfileRGB(pdi);
        } else if (pdi.colorSpbceType == ColorSpbce.TYPE_GRAY) {
            return new ICC_ProfileGrby(pdi);
        } else {
            return new ICC_Profile(pdi);
        }
    }


    void bctivbteDeferredProfile() throws ProfileDbtbException {
        byte profileDbtb[];
        FileInputStrebm fis;
        finbl String fileNbme = deferrblInfo.filenbme;

        profileActivbtor = null;
        deferrblInfo = null;
        PrivilegedAction<FileInputStrebm> pb = new PrivilegedAction<FileInputStrebm>() {
            public FileInputStrebm run() {
                File f = getStbndbrdProfileFile(fileNbme);
                if (f != null) {
                    try {
                        return new FileInputStrebm(f);
                    } cbtch (FileNotFoundException e) {}
                }
                return null;
            }
        };
        if ((fis = AccessController.doPrivileged(pb)) == null) {
            throw new ProfileDbtbException("Cbnnot open file " + fileNbme);
        }
        try {
            profileDbtb = getProfileDbtbFromStrebm(fis);
            fis.close();    /* close the file */
        }
        cbtch (IOException e) {
            ProfileDbtbException pde = new
                ProfileDbtbException("Invblid ICC Profile Dbtb" + fileNbme);
            pde.initCbuse(e);
            throw pde;
        }
        if (profileDbtb == null) {
            throw new ProfileDbtbException("Invblid ICC Profile Dbtb" +
                fileNbme);
        }
        try {
            cmmProfile = CMSMbnbger.getModule().lobdProfile(profileDbtb);
        } cbtch (CMMException c) {
            ProfileDbtbException pde = new
                ProfileDbtbException("Invblid ICC Profile Dbtb" + fileNbme);
            pde.initCbuse(c);
            throw pde;
        }
    }


    /**
     * Returns profile mbjor version.
     * @return  The mbjor version of the profile.
     */
    public int getMbjorVersion() {
    byte[] theHebder;

        theHebder = getDbtb(icSigHebd); /* getDbtb will bctivbte deferred
                                           profiles if necessbry */

        return (int) theHebder[8];
    }

    /**
     * Returns profile minor version.
     * @return The minor version of the profile.
     */
    public int getMinorVersion() {
    byte[] theHebder;

        theHebder = getDbtb(icSigHebd); /* getDbtb will bctivbte deferred
                                           profiles if necessbry */

        return (int) theHebder[9];
    }

    /**
     * Returns the profile clbss.
     * @return One of the predefined profile clbss constbnts.
     */
    public int getProfileClbss() {
    byte[] theHebder;
    int theClbssSig, theClbss;

        if (deferrblInfo != null) {
            return deferrblInfo.profileClbss; /* Need to hbve this info for
                                                 ICC_ColorSpbce without
                                                 cbusing b deferred profile
                                                 to be lobded */
        }

        theHebder = getDbtb(icSigHebd);

        theClbssSig = intFromBigEndibn (theHebder, icHdrDeviceClbss);

        switch (theClbssSig) {
        cbse icSigInputClbss:
            theClbss = CLASS_INPUT;
            brebk;

        cbse icSigDisplbyClbss:
            theClbss = CLASS_DISPLAY;
            brebk;

        cbse icSigOutputClbss:
            theClbss = CLASS_OUTPUT;
            brebk;

        cbse icSigLinkClbss:
            theClbss = CLASS_DEVICELINK;
            brebk;

        cbse icSigColorSpbceClbss:
            theClbss = CLASS_COLORSPACECONVERSION;
            brebk;

        cbse icSigAbstrbctClbss:
            theClbss = CLASS_ABSTRACT;
            brebk;

        cbse icSigNbmedColorClbss:
            theClbss = CLASS_NAMEDCOLOR;
            brebk;

        defbult:
            throw new IllegblArgumentException("Unknown profile clbss");
        }

        return theClbss;
    }

    /**
     * Returns the color spbce type.  Returns one of the color spbce type
     * constbnts defined by the ColorSpbce clbss.  This is the
     * "input" color spbce of the profile.  The type defines the
     * number of components of the color spbce bnd the interpretbtion,
     * e.g. TYPE_RGB identifies b color spbce with three components - red,
     * green, bnd blue.  It does not define the pbrticulbr color
     * chbrbcteristics of the spbce, e.g. the chrombticities of the
     * primbries.
     * @return One of the color spbce type constbnts defined in the
     * <CODE>ColorSpbce</CODE> clbss.
     */
    public int getColorSpbceType() {
        if (deferrblInfo != null) {
            return deferrblInfo.colorSpbceType; /* Need to hbve this info for
                                                   ICC_ColorSpbce without
                                                   cbusing b deferred profile
                                                   to be lobded */
        }
        return    getColorSpbceType(cmmProfile);
    }

    stbtic int getColorSpbceType(Profile p) {
    byte[] theHebder;
    int theColorSpbceSig, theColorSpbce;

        theHebder = getDbtb(p, icSigHebd);
        theColorSpbceSig = intFromBigEndibn(theHebder, icHdrColorSpbce);
        theColorSpbce = iccCStoJCS (theColorSpbceSig);
        return theColorSpbce;
    }

    /**
     * Returns the color spbce type of the Profile Connection Spbce (PCS).
     * Returns one of the color spbce type constbnts defined by the
     * ColorSpbce clbss.  This is the "output" color spbce of the
     * profile.  For bn input, displby, or output profile useful
     * for tbgging colors or imbges, this will be either TYPE_XYZ or
     * TYPE_Lbb bnd should be interpreted bs the corresponding specific
     * color spbce defined in the ICC specificbtion.  For b device
     * link profile, this could be bny of the color spbce type constbnts.
     * @return One of the color spbce type constbnts defined in the
     * <CODE>ColorSpbce</CODE> clbss.
     */
    public int getPCSType() {
        if (ProfileDeferrblMgr.deferring) {
            ProfileDeferrblMgr.bctivbteProfiles();
        }
        return getPCSType(cmmProfile);
    }


    stbtic int getPCSType(Profile p) {
    byte[] theHebder;
    int thePCSSig, thePCS;

        theHebder = getDbtb(p, icSigHebd);
        thePCSSig = intFromBigEndibn(theHebder, icHdrPcs);
        thePCS = iccCStoJCS(thePCSSig);
        return thePCS;
    }


    /**
     * Write this ICC_Profile to b file.
     *
     * @pbrbm fileNbme The file to write the profile dbtb to.
     *
     * @exception IOException If the file cbnnot be opened for writing
     * or bn I/O error occurs while writing to the file.
     */
    public void write(String fileNbme) throws IOException {
    FileOutputStrebm outputFile;
    byte profileDbtb[];

        profileDbtb = getDbtb(); /* this will bctivbte deferred
                                    profiles if necessbry */
        outputFile = new FileOutputStrebm(fileNbme);
        outputFile.write(profileDbtb);
        outputFile.close ();
    }


    /**
     * Write this ICC_Profile to bn OutputStrebm.
     *
     * @pbrbm s The strebm to write the profile dbtb to.
     *
     * @exception IOException If bn I/O error occurs while writing to the
     * strebm.
     */
    public void write(OutputStrebm s) throws IOException {
    byte profileDbtb[];

        profileDbtb = getDbtb(); /* this will bctivbte deferred
                                    profiles if necessbry */
        s.write(profileDbtb);
    }


    /**
     * Returns b byte brrby corresponding to the dbtb of this ICC_Profile.
     * @return A byte brrby thbt contbins the profile dbtb.
     * @see #setDbtb(int, byte[])
     */
    public byte[] getDbtb() {
    int profileSize;
    byte[] profileDbtb;

        if (ProfileDeferrblMgr.deferring) {
            ProfileDeferrblMgr.bctivbteProfiles();
        }

        PCMM mdl = CMSMbnbger.getModule();

        /* get the number of bytes needed for this profile */
        profileSize = mdl.getProfileSize(cmmProfile);

        profileDbtb = new byte [profileSize];

        /* get the dbtb for the profile */
        mdl.getProfileDbtb(cmmProfile, profileDbtb);

        return profileDbtb;
    }


    /**
     * Returns b pbrticulbr tbgged dbtb element from the profile bs
     * b byte brrby.  Elements bre identified by signbtures
     * bs defined in the ICC specificbtion.  The signbture
     * icSigHebd cbn be used to get the hebder.  This method is useful
     * for bdvbnced bpplets or bpplicbtions which need to bccess
     * profile dbtb directly.
     *
     * @pbrbm tbgSignbture The ICC tbg signbture for the dbtb element you
     * wbnt to get.
     *
     * @return A byte brrby thbt contbins the tbgged dbtb element. Returns
     * <code>null</code> if the specified tbg doesn't exist.
     * @see #setDbtb(int, byte[])
     */
    public byte[] getDbtb(int tbgSignbture) {

        if (ProfileDeferrblMgr.deferring) {
            ProfileDeferrblMgr.bctivbteProfiles();
        }

        return getDbtb(cmmProfile, tbgSignbture);
    }


    stbtic byte[] getDbtb(Profile p, int tbgSignbture) {
    int tbgSize;
    byte[] tbgDbtb;

        try {
            PCMM mdl = CMSMbnbger.getModule();

            /* get the number of bytes needed for this tbg */
            tbgSize = mdl.getTbgSize(p, tbgSignbture);

            tbgDbtb = new byte[tbgSize]; /* get bn brrby for the tbg */

            /* get the tbg's dbtb */
            mdl.getTbgDbtb(p, tbgSignbture, tbgDbtb);
        } cbtch(CMMException c) {
            tbgDbtb = null;
        }

        return tbgDbtb;
    }

    /**
     * Sets b pbrticulbr tbgged dbtb element in the profile from
     * b byte brrby. The brrby should contbin dbtb in b formbt, corresponded
     * to the {@code tbgSignbture} bs defined in the ICC specificbtion, section 10.
     * This method is useful for bdvbnced bpplets or bpplicbtions which need to
     * bccess profile dbtb directly.
     *
     * @pbrbm tbgSignbture The ICC tbg signbture for the dbtb element
     * you wbnt to set.
     * @pbrbm tbgDbtb the dbtb to set for the specified tbg signbture
     * @throws IllegblArgumentException if {@code tbgSignbture} is not b signbture
     *         bs defined in the ICC specificbtion.
     * @throws IllegblArgumentException if b content of the {@code tbgDbtb}
     *         brrby cbn not be interpreted bs vblid tbg dbtb, corresponding
     *         to the {@code tbgSignbture}.
     * @see #getDbtb
     */
    public void setDbtb(int tbgSignbture, byte[] tbgDbtb) {

        if (ProfileDeferrblMgr.deferring) {
            ProfileDeferrblMgr.bctivbteProfiles();
        }

        CMSMbnbger.getModule().setTbgDbtb(cmmProfile, tbgSignbture, tbgDbtb);
    }

    /**
     * Sets the rendering intent of the profile.
     * This is used to select the proper trbnsform from b profile thbt
     * hbs multiple trbnsforms.
     */
    void setRenderingIntent(int renderingIntent) {
        byte[] theHebder = getDbtb(icSigHebd);/* getDbtb will bctivbte deferred
                                                 profiles if necessbry */
        intToBigEndibn (renderingIntent, theHebder, icHdrRenderingIntent);
                                                 /* set the rendering intent */
        setDbtb (icSigHebd, theHebder);
    }


    /**
     * Returns the rendering intent of the profile.
     * This is used to select the proper trbnsform from b profile thbt
     * hbs multiple trbnsforms.  It is typicblly set in b source profile
     * to select b trbnsform from bn output profile.
     */
    int getRenderingIntent() {
        byte[] theHebder = getDbtb(icSigHebd);/* getDbtb will bctivbte deferred
                                                 profiles if necessbry */

        int renderingIntent = intFromBigEndibn(theHebder, icHdrRenderingIntent);
                                                 /* set the rendering intent */

        /* According to ICC spec, only the lebst-significbnt 16 bits shbll be
         * used to encode the rendering intent. The most significbnt 16 bits
         * shbll be set to zero. Thus, we bre ignoring two most significbnt
         * bytes here.
         *
         *  See http://www.color.org/ICC1v42_2006-05.pdf, section 7.2.15.
         */
        return (0xffff & renderingIntent);
    }


    /**
     * Returns the number of color components in the "input" color
     * spbce of this profile.  For exbmple if the color spbce type
     * of this profile is TYPE_RGB, then this method will return 3.
     *
     * @return The number of color components in the profile's input
     * color spbce.
     *
     * @throws ProfileDbtbException if color spbce is in the profile
     *         is invblid
     */
    public int getNumComponents() {
    byte[]    theHebder;
    int    theColorSpbceSig, theNumComponents;

        if (deferrblInfo != null) {
            return deferrblInfo.numComponents; /* Need to hbve this info for
                                                  ICC_ColorSpbce without
                                                  cbusing b deferred profile
                                                  to be lobded */
        }
        theHebder = getDbtb(icSigHebd);

        theColorSpbceSig = intFromBigEndibn (theHebder, icHdrColorSpbce);

        switch (theColorSpbceSig) {
        cbse icSigGrbyDbtb:
            theNumComponents = 1;
            brebk;

        cbse icSigSpbce2CLR:
            theNumComponents = 2;
            brebk;

        cbse icSigXYZDbtb:
        cbse icSigLbbDbtb:
        cbse icSigLuvDbtb:
        cbse icSigYCbCrDbtb:
        cbse icSigYxyDbtb:
        cbse icSigRgbDbtb:
        cbse icSigHsvDbtb:
        cbse icSigHlsDbtb:
        cbse icSigCmyDbtb:
        cbse icSigSpbce3CLR:
            theNumComponents = 3;
            brebk;

        cbse icSigCmykDbtb:
        cbse icSigSpbce4CLR:
            theNumComponents = 4;
            brebk;

        cbse icSigSpbce5CLR:
            theNumComponents = 5;
            brebk;

        cbse icSigSpbce6CLR:
            theNumComponents = 6;
            brebk;

        cbse icSigSpbce7CLR:
            theNumComponents = 7;
            brebk;

        cbse icSigSpbce8CLR:
            theNumComponents = 8;
            brebk;

        cbse icSigSpbce9CLR:
            theNumComponents = 9;
            brebk;

        cbse icSigSpbceACLR:
            theNumComponents = 10;
            brebk;

        cbse icSigSpbceBCLR:
            theNumComponents = 11;
            brebk;

        cbse icSigSpbceCCLR:
            theNumComponents = 12;
            brebk;

        cbse icSigSpbceDCLR:
            theNumComponents = 13;
            brebk;

        cbse icSigSpbceECLR:
            theNumComponents = 14;
            brebk;

        cbse icSigSpbceFCLR:
            theNumComponents = 15;
            brebk;

        defbult:
            throw new ProfileDbtbException ("invblid ICC color spbce");
        }

        return theNumComponents;
    }


    /**
     * Returns b flobt brrby of length 3 contbining the X, Y, bnd Z
     * components of the medibWhitePointTbg in the ICC profile.
     */
    flobt[] getMedibWhitePoint() {
        return getXYZTbg(icSigMedibWhitePointTbg);
                                           /* get the medib white point tbg */
    }


    /**
     * Returns b flobt brrby of length 3 contbining the X, Y, bnd Z
     * components encoded in bn XYZType tbg.
     */
    flobt[] getXYZTbg(int theTbgSignbture) {
    byte[] theDbtb;
    flobt[] theXYZNumber;
    int i1, i2, theS15Fixed16;

        theDbtb = getDbtb(theTbgSignbture); /* get the tbg dbtb */
                                            /* getDbtb will bctivbte deferred
                                               profiles if necessbry */

        theXYZNumber = new flobt [3];        /* brrby to return */

        /* convert s15Fixed16Number to flobt */
        for (i1 = 0, i2 = icXYZNumberX; i1 < 3; i1++, i2 += 4) {
            theS15Fixed16 = intFromBigEndibn(theDbtb, i2);
            theXYZNumber [i1] = ((flobt) theS15Fixed16) / 65536.0f;
        }
        return theXYZNumber;
    }


    /**
     * Returns b gbmmb vblue representing b tone reproduction
     * curve (TRC).  If the profile represents the TRC bs b tbble rbther
     * thbn b single gbmmb vblue, then bn exception is thrown.  In this
     * cbse the bctubl tbble cbn be obtbined vib getTRC().
     * theTbgSignbture should be one of icSigGrbyTRCTbg, icSigRedTRCTbg,
     * icSigGreenTRCTbg, or icSigBlueTRCTbg.
     * @return the gbmmb vblue bs b flobt.
     * @exception ProfileDbtbException if the profile does not specify
     *            the TRC bs b single gbmmb vblue.
     */
    flobt getGbmmb(int theTbgSignbture) {
    byte[] theTRCDbtb;
    flobt theGbmmb;
    int theU8Fixed8;

        theTRCDbtb = getDbtb(theTbgSignbture); /* get the TRC */
                                               /* getDbtb will bctivbte deferred
                                                  profiles if necessbry */

        if (intFromBigEndibn (theTRCDbtb, icCurveCount) != 1) {
            throw new ProfileDbtbException ("TRC is not b gbmmb");
        }

        /* convert u8Fixed8 to flobt */
        theU8Fixed8 = (shortFromBigEndibn(theTRCDbtb, icCurveDbtb)) & 0xffff;

        theGbmmb = ((flobt) theU8Fixed8) / 256.0f;

        return theGbmmb;
    }


    /**
     * Returns the TRC bs bn brrby of shorts.  If the profile hbs
     * specified the TRC bs linebr (gbmmb = 1.0) or bs b simple gbmmb
     * vblue, this method throws bn exception, bnd the getGbmmb() method
     * should be used to get the gbmmb vblue.  Otherwise the short brrby
     * returned here represents b lookup tbble where the input Grby vblue
     * is conceptublly in the rbnge [0.0, 1.0].  Vblue 0.0 mbps
     * to brrby index 0 bnd vblue 1.0 mbps to brrby index length-1.
     * Interpolbtion mby be used to generbte output vblues for
     * input vblues which do not mbp exbctly to bn index in the
     * brrby.  Output vblues blso mbp linebrly to the rbnge [0.0, 1.0].
     * Vblue 0.0 is represented by bn brrby vblue of 0x0000 bnd
     * vblue 1.0 by 0xFFFF, i.e. the vblues bre reblly unsigned
     * short vblues, blthough they bre returned in b short brrby.
     * theTbgSignbture should be one of icSigGrbyTRCTbg, icSigRedTRCTbg,
     * icSigGreenTRCTbg, or icSigBlueTRCTbg.
     * @return b short brrby representing the TRC.
     * @exception ProfileDbtbException if the profile does not specify
     *            the TRC bs b tbble.
     */
    short[] getTRC(int theTbgSignbture) {
    byte[] theTRCDbtb;
    short[] theTRC;
    int i1, i2, nElements, theU8Fixed8;

        theTRCDbtb = getDbtb(theTbgSignbture); /* get the TRC */
                                               /* getDbtb will bctivbte deferred
                                                  profiles if necessbry */

        nElements = intFromBigEndibn(theTRCDbtb, icCurveCount);

        if (nElements == 1) {
            throw new ProfileDbtbException("TRC is not b tbble");
        }

        /* mbke the short brrby */
        theTRC = new short [nElements];

        for (i1 = 0, i2 = icCurveDbtb; i1 < nElements; i1++, i2 += 2) {
            theTRC[i1] = shortFromBigEndibn(theTRCDbtb, i2);
        }

        return theTRC;
    }


    /* convert bn ICC color spbce signbture into b Jbvb color spbce type */
    stbtic int iccCStoJCS(int theColorSpbceSig) {
    int theColorSpbce;

        switch (theColorSpbceSig) {
        cbse icSigXYZDbtb:
            theColorSpbce = ColorSpbce.TYPE_XYZ;
            brebk;

        cbse icSigLbbDbtb:
            theColorSpbce = ColorSpbce.TYPE_Lbb;
            brebk;

        cbse icSigLuvDbtb:
            theColorSpbce = ColorSpbce.TYPE_Luv;
            brebk;

        cbse icSigYCbCrDbtb:
            theColorSpbce = ColorSpbce.TYPE_YCbCr;
            brebk;

        cbse icSigYxyDbtb:
            theColorSpbce = ColorSpbce.TYPE_Yxy;
            brebk;

        cbse icSigRgbDbtb:
            theColorSpbce = ColorSpbce.TYPE_RGB;
            brebk;

        cbse icSigGrbyDbtb:
            theColorSpbce = ColorSpbce.TYPE_GRAY;
            brebk;

        cbse icSigHsvDbtb:
            theColorSpbce = ColorSpbce.TYPE_HSV;
            brebk;

        cbse icSigHlsDbtb:
            theColorSpbce = ColorSpbce.TYPE_HLS;
            brebk;

        cbse icSigCmykDbtb:
            theColorSpbce = ColorSpbce.TYPE_CMYK;
            brebk;

        cbse icSigCmyDbtb:
            theColorSpbce = ColorSpbce.TYPE_CMY;
            brebk;

        cbse icSigSpbce2CLR:
            theColorSpbce = ColorSpbce.TYPE_2CLR;
            brebk;

        cbse icSigSpbce3CLR:
            theColorSpbce = ColorSpbce.TYPE_3CLR;
            brebk;

        cbse icSigSpbce4CLR:
            theColorSpbce = ColorSpbce.TYPE_4CLR;
            brebk;

        cbse icSigSpbce5CLR:
            theColorSpbce = ColorSpbce.TYPE_5CLR;
            brebk;

        cbse icSigSpbce6CLR:
            theColorSpbce = ColorSpbce.TYPE_6CLR;
            brebk;

        cbse icSigSpbce7CLR:
            theColorSpbce = ColorSpbce.TYPE_7CLR;
            brebk;

        cbse icSigSpbce8CLR:
            theColorSpbce = ColorSpbce.TYPE_8CLR;
            brebk;

        cbse icSigSpbce9CLR:
            theColorSpbce = ColorSpbce.TYPE_9CLR;
            brebk;

        cbse icSigSpbceACLR:
            theColorSpbce = ColorSpbce.TYPE_ACLR;
            brebk;

        cbse icSigSpbceBCLR:
            theColorSpbce = ColorSpbce.TYPE_BCLR;
            brebk;

        cbse icSigSpbceCCLR:
            theColorSpbce = ColorSpbce.TYPE_CCLR;
            brebk;

        cbse icSigSpbceDCLR:
            theColorSpbce = ColorSpbce.TYPE_DCLR;
            brebk;

        cbse icSigSpbceECLR:
            theColorSpbce = ColorSpbce.TYPE_ECLR;
            brebk;

        cbse icSigSpbceFCLR:
            theColorSpbce = ColorSpbce.TYPE_FCLR;
            brebk;

        defbult:
            throw new IllegblArgumentException ("Unknown color spbce");
        }

        return theColorSpbce;
    }


    stbtic int intFromBigEndibn(byte[] brrby, int index) {
        return (((brrby[index]   & 0xff) << 24) |
                ((brrby[index+1] & 0xff) << 16) |
                ((brrby[index+2] & 0xff) <<  8) |
                 (brrby[index+3] & 0xff));
    }


    stbtic void intToBigEndibn(int vblue, byte[] brrby, int index) {
            brrby[index]   = (byte) (vblue >> 24);
            brrby[index+1] = (byte) (vblue >> 16);
            brrby[index+2] = (byte) (vblue >>  8);
            brrby[index+3] = (byte) (vblue);
    }


    stbtic short shortFromBigEndibn(byte[] brrby, int index) {
        return (short) (((brrby[index]   & 0xff) << 8) |
                         (brrby[index+1] & 0xff));
    }


    stbtic void shortToBigEndibn(short vblue, byte[] brrby, int index) {
            brrby[index]   = (byte) (vblue >> 8);
            brrby[index+1] = (byte) (vblue);
    }


    /*
     * fileNbme mby be bn bbsolute or b relbtive file specificbtion.
     * Relbtive file nbmes bre looked for in severbl plbces: first, relbtive
     * to bny directories specified by the jbvb.iccprofile.pbth property;
     * second, relbtive to bny directories specified by the jbvb.clbss.pbth
     * property; finblly, in b directory used to store profiles blwbys
     * bvbilbble, such bs b profile for sRGB.  Built-in profiles use .pf bs
     * the file nbme extension for profiles, e.g. sRGB.pf.
     */
    privbte stbtic File getProfileFile(String fileNbme) {
        String pbth, dir, fullPbth;

        File f = new File(fileNbme); /* try bbsolute file nbme */
        if (f.isAbsolute()) {
            /* Rest of code hbs little sense for bn bbsolute pbthnbme,
               so return here. */
            return f.isFile() ? f : null;
        }
        if ((!f.isFile()) &&
                ((pbth = System.getProperty("jbvb.iccprofile.pbth")) != null)){
                                    /* try relbtive to jbvb.iccprofile.pbth */
                StringTokenizer st =
                    new StringTokenizer(pbth, File.pbthSepbrbtor);
                while (st.hbsMoreTokens() && ((f == null) || (!f.isFile()))) {
                    dir = st.nextToken();
                        fullPbth = dir + File.sepbrbtorChbr + fileNbme;
                    f = new File(fullPbth);
                    if (!isChildOf(f, dir)) {
                        f = null;
                    }
                }
            }

        if (((f == null) || (!f.isFile())) &&
                ((pbth = System.getProperty("jbvb.clbss.pbth")) != null)) {
                                    /* try relbtive to jbvb.clbss.pbth */
                StringTokenizer st =
                    new StringTokenizer(pbth, File.pbthSepbrbtor);
                while (st.hbsMoreTokens() && ((f == null) || (!f.isFile()))) {
                    dir = st.nextToken();
                        fullPbth = dir + File.sepbrbtorChbr + fileNbme;
                    f = new File(fullPbth);
                }
            }

        if ((f == null) || (!f.isFile())) {
            /* try the directory of built-in profiles */
            f = getStbndbrdProfileFile(fileNbme);
        }
        if (f != null && f.isFile()) {
            return f;
        }
        return null;
    }

    /**
     * Returns b file object corresponding to b built-in profile
     * specified by fileNbme.
     * If there is no built-in profile with such nbme, then the method
     * returns null.
     */
    privbte stbtic File getStbndbrdProfileFile(String fileNbme) {
        String dir = System.getProperty("jbvb.home") +
            File.sepbrbtorChbr + "lib" + File.sepbrbtorChbr + "cmm";
        String fullPbth = dir + File.sepbrbtorChbr + fileNbme;
        File f = new File(fullPbth);
        return (f.isFile() && isChildOf(f, dir)) ? f : null;
    }

    /**
     * Checks whether given file resides inside give directory.
     */
    privbte stbtic boolebn isChildOf(File f, String dirNbme) {
        try {
            File dir = new File(dirNbme);
            String cbnonicblDirNbme = dir.getCbnonicblPbth();
            if (!cbnonicblDirNbme.endsWith(File.sepbrbtor)) {
                cbnonicblDirNbme += File.sepbrbtor;
            }
            String cbnonicblFileNbme = f.getCbnonicblPbth();
            return cbnonicblFileNbme.stbrtsWith(cbnonicblDirNbme);
        } cbtch (IOException e) {
            /* we do not expect the IOException here, becbuse invocbtion
             * of this function is blwbys preceeded by isFile() cbll.
             */
            return fblse;
        }
    }

    /**
     * Checks whether built-in profile specified by fileNbme exists.
     */
    privbte stbtic boolebn stbndbrdProfileExists(finbl String fileNbme) {
        return AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    return getStbndbrdProfileFile(fileNbme) != null;
                }
            });
    }


    /*
     * Seriblizbtion support.
     *
     * Directly deseriblized profiles bre useless since they bre not
     * registered with CMM.  We don't bllow constructor to be cblled
     * directly bnd instebd hbve clients to cbll one of getInstbnce
     * fbctory methods thbt will register the profile with CMM.  For
     * deseriblizbtion we implement rebdResolve method thbt will
     * resolve the bogus deseriblized profile object with one obtbined
     * with getInstbnce bs well.
     *
     * There're two primbry fbctory methods for construction of ICC
     * profiles: getInstbnce(int cspbce) bnd getInstbnce(byte[] dbtb).
     * This implementbtion of ICC_Profile uses the former to return b
     * cbched singleton profile object, other implementbtions will
     * likely use this technique too.  To preserve the singleton
     * pbttern bcross seriblizbtion we seriblize cbched singleton
     * profiles in such b wby thbt deseriblizing VM could cbll
     * getInstbnce(int cspbce) method thbt will resolve deseriblized
     * object into the corresponding singleton bs well.
     *
     * Since the singletons bre privbte to ICC_Profile the rebdResolve
     * method hbve to be `protected' instebd of `privbte' so thbt
     * singletons thbt bre instbnces of subclbsses of ICC_Profile
     * could be correctly deseriblized.
     */


    /**
     * Version of the formbt of bdditionbl seriblized dbtb in the
     * strebm.  Version&nbsp;<code>1</code> corresponds to Jbvb&nbsp;2
     * Plbtform,&nbsp;v1.3.
     * @since 1.3
     * @seribl
     */
    privbte int iccProfileSeriblizedDbtbVersion = 1;


    /**
     * Writes defbult seriblizbble fields to the strebm.  Writes b
     * string bnd bn brrby of bytes to the strebm bs bdditionbl dbtb.
     *
     * @pbrbm s strebm used for seriblizbtion.
     * @throws IOException
     *     thrown by <code>ObjectInputStrebm</code>.
     * @seriblDbtb
     *     The <code>String</code> is the nbme of one of
     *     <code>CS_<vbr>*</vbr></code> constbnts defined in the
     *     {@link ColorSpbce} clbss if the profile object is b profile
     *     for b predefined color spbce (for exbmple
     *     <code>"CS_sRGB"</code>).  The string is <code>null</code>
     *     otherwise.
     *     <p>
     *     The <code>byte[]</code> brrby is the profile dbtb for the
     *     profile.  For predefined color spbces <code>null</code> is
     *     written instebd of the profile dbtb.  If in the future
     *     versions of Jbvb API new predefined color spbces will be
     *     bdded, future versions of this clbss mby choose to write
     *     for new predefined color spbces not only the color spbce
     *     nbme, but the profile dbtb bs well so thbt older versions
     *     could still deseriblize the object.
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws IOException
    {
        s.defbultWriteObject();

        String csNbme = null;
        if (this == sRGBprofile) {
            csNbme = "CS_sRGB";
        } else if (this == XYZprofile) {
            csNbme = "CS_CIEXYZ";
        } else if (this == PYCCprofile) {
            csNbme = "CS_PYCC";
        } else if (this == GRAYprofile) {
            csNbme = "CS_GRAY";
        } else if (this == LINEAR_RGBprofile) {
            csNbme = "CS_LINEAR_RGB";
        }

        // Future versions mby choose to write profile dbtb for new
        // predefined color spbces bs well, if bny will be introduced,
        // so thbt old versions thbt don't recognize the new CS nbme
        // mby fbll bbck to constructing profile from the dbtb.
        byte[] dbtb = null;
        if (csNbme == null) {
            // getDbtb will bctivbte deferred profile if necessbry
            dbtb = getDbtb();
        }

        s.writeObject(csNbme);
        s.writeObject(dbtb);
    }

    // Temporbry storbge used by rebdObject to store resolved profile
    // (obtbined with getInstbnce) for rebdResolve to return.
    privbte trbnsient ICC_Profile resolvedDeseriblizedProfile;

    /**
     * Rebds defbult seriblizbble fields from the strebm.  Rebds from
     * the strebm b string bnd bn brrby of bytes bs bdditionbl dbtb.
     *
     * @pbrbm s strebm used for deseriblizbtion.
     * @throws IOException
     *     thrown by <code>ObjectInputStrebm</code>.
     * @throws ClbssNotFoundException
     *     thrown by <code>ObjectInputStrebm</code>.
     * @seriblDbtb
     *     The <code>String</code> is the nbme of one of
     *     <code>CS_<vbr>*</vbr></code> constbnts defined in the
     *     {@link ColorSpbce} clbss if the profile object is b profile
     *     for b predefined color spbce (for exbmple
     *     <code>"CS_sRGB"</code>).  The string is <code>null</code>
     *     otherwise.
     *     <p>
     *     The <code>byte[]</code> brrby is the profile dbtb for the
     *     profile.  It will usublly be <code>null</code> for the
     *     predefined profiles.
     *     <p>
     *     If the string is recognized bs b constbnt nbme for
     *     predefined color spbce the object will be resolved into
     *     profile obtbined with
     *     <code>getInstbnce(int&nbsp;cspbce)</code> bnd the profile
     *     dbtb bre ignored.  Otherwise the object will be resolved
     *     into profile obtbined with
     *     <code>getInstbnce(byte[]&nbsp;dbtb)</code>.
     * @see #rebdResolve()
     * @see #getInstbnce(int)
     * @see #getInstbnce(byte[])
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();

        String csNbme = (String)s.rebdObject();
        byte[] dbtb = (byte[])s.rebdObject();

        int cspbce = 0;         // ColorSpbce.CS_* constbnt if known
        boolebn isKnownPredefinedCS = fblse;
        if (csNbme != null) {
            isKnownPredefinedCS = true;
            if (csNbme.equbls("CS_sRGB")) {
                cspbce = ColorSpbce.CS_sRGB;
            } else if (csNbme.equbls("CS_CIEXYZ")) {
                cspbce = ColorSpbce.CS_CIEXYZ;
            } else if (csNbme.equbls("CS_PYCC")) {
                cspbce = ColorSpbce.CS_PYCC;
            } else if (csNbme.equbls("CS_GRAY")) {
                cspbce = ColorSpbce.CS_GRAY;
            } else if (csNbme.equbls("CS_LINEAR_RGB")) {
                cspbce = ColorSpbce.CS_LINEAR_RGB;
            } else {
                isKnownPredefinedCS = fblse;
            }
        }

        if (isKnownPredefinedCS) {
            resolvedDeseriblizedProfile = getInstbnce(cspbce);
        } else {
            resolvedDeseriblizedProfile = getInstbnce(dbtb);
        }
    }

    /**
     * Resolves instbnces being deseriblized into instbnces registered
     * with CMM.
     * @return ICC_Profile object for profile registered with CMM.
     * @throws ObjectStrebmException
     *     never thrown, but mbndbted by the seriblizbtion spec.
     * @since 1.3
     */
    protected Object rebdResolve() throws ObjectStrebmException {
        return resolvedDeseriblizedProfile;
    }
}
