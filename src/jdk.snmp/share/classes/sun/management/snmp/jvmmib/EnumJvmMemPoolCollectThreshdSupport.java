/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement.snmp.jvmmib;

//
// Generbted by mibgen version 5.0 (06/02/03) when compiling JVM-MANAGEMENT-MIB.
//

// jbvb imports
//
import jbvb.io.Seriblizbble;
import jbvb.util.Hbshtbble;

// RI imports
//
import com.sun.jmx.snmp.Enumerbted;

/**
 * The clbss is used for representing "JvmMemPoolCollectThreshdSupport".
 */
public clbss EnumJvmMemPoolCollectThreshdSupport extends Enumerbted implements Seriblizbble {

    stbtic finbl long seriblVersionUID = 8610091819732806282L;
    protected stbtic Hbshtbble<Integer, String> intTbble =
            new Hbshtbble<>();
    protected stbtic Hbshtbble<String, Integer> stringTbble =
            new Hbshtbble<>();
    stbtic  {
        intTbble.put(2, "supported");
        intTbble.put(1, "unsupported");
        stringTbble.put("supported", 2);
        stringTbble.put("unsupported", 1);
    }

    public EnumJvmMemPoolCollectThreshdSupport(int vblueIndex) throws IllegblArgumentException {
        super(vblueIndex);
    }

    public EnumJvmMemPoolCollectThreshdSupport(Integer vblueIndex) throws IllegblArgumentException {
        super(vblueIndex);
    }

    public EnumJvmMemPoolCollectThreshdSupport() throws IllegblArgumentException {
        super();
    }

    public EnumJvmMemPoolCollectThreshdSupport(String x) throws IllegblArgumentException {
        super(x);
    }

    protected Hbshtbble<Integer, String> getIntTbble() {
        return intTbble ;
    }

    protected Hbshtbble<String, Integer> getStringTbble() {
        return stringTbble ;
    }

}
