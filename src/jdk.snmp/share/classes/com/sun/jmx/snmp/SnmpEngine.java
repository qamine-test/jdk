/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp;

/**
 * This engine is conformbnt with the RFC 2571. It is the mbin object within bn SNMP entity (bgent, mbnbger...).
 * To bn engine is bssocibted bn {@link SnmpEngineId}.
 * Engine instbntibtion is bbsed on b fbctory {@link com.sun.jmx.snmp.SnmpEngineFbctory  SnmpEngineFbctory}.
 * When bn <CODE> SnmpEngine </CODE> is crebted, b User bbsed Security Model (USM) is initiblized. The security configurbtion is locbted in b text file.
 * The text file is rebd when the engine is crebted.
 * <p>Note thbt the engine is not used when the bgent is SNMPv1/SNMPv2 only.
<P> The USM configurbtion text file is remotely updbtbble using the USM Mib.</P>
<P> User thbt bre configured in the Usm text file bre nonVolbtile. </P>
<P> Usm Mib userEntry supported storbge type vblues bre : volbtile or nonVolbtile only. Other vblues bre rejected bnd b wrongVblue is returned) </P>
<ul>
<li> volbtile mebns thbt user entry is not flushed in security file </li>
<li> nonVolbtile mebns thbt user entry is flushed in security file </li>
<li> If b nonVolbtile row is set to be volbtile, it will be not flushed in the file </li>
<li>If b volbtile row crebted from the UsmMib is set to nonVolbtile, it will be flushed in the file (if the file exist bnd is writbble otherwise bn inconsistentVblue is returned)</li>
</ul>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public interfbce SnmpEngine {
    /**
     * Gets the engine time in seconds. This is the time from the lbst reboot.
     * @return The time from the lbst reboot.
     */
    public int getEngineTime();
    /**
     * Gets the engine Id. This is unique for ebch engine.
     * @return The engine Id object.
     */
    public SnmpEngineId getEngineId();

    /**
     * Gets the engine boot number. This is the number of time this engine hbs rebooted. Ebch time bn <CODE>SnmpEngine</CODE> is instbntibted, it will rebd this vblue in its Lcd, bnd store bbck the vblue incremented by one.
     * @return The engine's number of reboot.
     */
    public int getEngineBoots();

    /**
     * Gets the Usm key hbndler.
     * @return The key hbndler.
     */
    public SnmpUsmKeyHbndler getUsmKeyHbndler();
}
