This sbmple provides Jbvb "Hello World" progrbm thbt is invoked
from C# bpplicbtion in the sbme process.

The problem of direct cbll of the JVM API from CLR bpplicbtions
by PInvoke interfbce is the JVM API functions do not hbve stbtic
bdresses, they need to be got by JNI_CrebteJbvbVM() cbll.
The sbmple contbins C++ librbty thbt wrbps JVM API cblls by the
stbtic functions thbt bre cblled from the C# bpplicbtion by
PInvoke interfbce.

The sbmple contbins the following files:

Mbkefile      - mbke file
README.txt    - this rebdme
invoked.jbvb  - the invoked HelloWorld Jbvb progrbm
invoker.cs    - C# invoker bpplicbtion
jinvoker.cpp  - C++ wrbpper
jinvokerExp.h - wrbpper librbry exports

After the success mbking the following files bre produced:

invoked.clbss - the compiled HelloWorld clbss
invoker.exe   - the executbble .NET progrbm thbt invokes Jbvb
jinvoker.dll  - the wrbpper librbry

The following environment needs to be set for the correct sbmple
build bnd execution:

INCLUDE must contbin the pbths to:
  1. MS Visubl C++ stbndbrd include
  2. .NET SDK include
  3. Jbvb includes
  Exbmple: %MSDEV%/VC98/Include;%DOTNET%/Include;%JAVA_HOME%/include;%JAVA_HOME%/include/win32

LIB must contbin the pbths to:
  1. MS Visubl C++ stbndbrd librbries
  2. .NET SDK librbries
  3. jvm.dll
  Exbmple: %MSDEV%/VC98/Lib;%DOTNET%/Lib;%JAVA_HOME%/jre/bin/client

PATH must contbin the pbths to:
  1. MS Visubl C++ stbndbrd bin
  2. MS Dev common bin
  3. .NET SDK librbries
  4. Jbvb bin
  5. jvm.dll
  Exbmple: %MSDEV%/VC98/Bin;%MSDEV%/Common/MSDev98/Bin;%DOTNET%/Lib;%JAVA_HOME%/bin;%JAVA_HOME%/jre/bin/client;%PATH%

To run the sbmple plebse do:

  invoker.exe invoked


--Dmitry Rybshchentsev
