import { Card } from "@/components/ui/card";
import DashboardLayout from "@/components/DashboardLayout";

const Dashboard = () => {
  const articles = [
    {
      name: "Overview of popular languages like Python, Java",
      views: 30,
    },
    {
      name: "Understanding threats, safe online practices",
      views: 15,
    },
    {
      name: "Exploring HTML, CSS, and JavaScript to create",
      views: 5,
    },
    {
      name: "Overview of machine learning, neural networks",
      views: 98,
    },
    {
      name: "Understanding cloud platforms like AWS, Azure",
      views: 74,
    },
  ];

  return (
    <DashboardLayout>
      <div className="space-y-8 animate-fade-in">
        <div className="flex justify-between items-center">
          <h1 className="text-2xl font-bold">Dashboard</h1>
          <div className="flex gap-2">
            <button className="px-4 py-2 rounded-lg hover:bg-gray-100">
              Yesterday
            </button>
            <button className="px-4 py-2 rounded-lg hover:bg-gray-100">
              Today
            </button>
            <button className="px-4 py-2 rounded-lg bg-primary text-white">
              Last 7 days
            </button>
            <button className="px-4 py-2 rounded-lg hover:bg-gray-100">
              Last 30 days
            </button>
            <button className="px-4 py-2 rounded-lg hover:bg-gray-100">
              Last 90 days
            </button>
            <button className="px-4 py-2 rounded-lg hover:bg-gray-100">
              Custom Range
            </button>
          </div>
        </div>

        <Card className="p-6">
          <h2 className="text-xl font-semibold mb-4">Top Performed Articles</h2>
          <div className="overflow-x-auto">
            <table className="w-full">
              <thead>
                <tr className="border-b">
                  <th className="text-left py-2">Article Name</th>
                  <th className="text-left py-2">Views</th>
                  <th className="text-left py-2">Action</th>
                </tr>
              </thead>
              <tbody>
                {articles.map((article, index) => (
                  <tr key={index} className="border-b">
                    <td className="py-2">{article.name}</td>
                    <td className="py-2">{article.views}</td>
                    <td className="py-2">
                      <a href="#" className="text-primary hover:text-primary-hover">
                        More Detail
                      </a>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </Card>

        {/* Placeholder for world map visualization */}
        <Card className="p-6 h-96 bg-accent">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-semibold">Global Article Views</h2>
            <button className="p-2 hover:bg-gray-100 rounded-lg">
              <svg
                className="w-5 h-5"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M4 8V4m0 0h4M4 4l5 5m11-1V4m0 0h-4m4 0l-5 5M4 16v4m0 0h4m-4 0l5-5m11 5l-5-5m5 5v-4m0 4h-4"
                />
              </svg>
            </button>
          </div>
          <div className="h-full bg-gray-100 rounded-lg flex items-center justify-center">
            World Map Visualization Placeholder
          </div>
        </Card>
      </div>
    </DashboardLayout>
  );
};



export default Dashboard;



//
//
// import { useState, useEffect } from 'react';
// import { Card } from "@/components/ui/card";
// import DashboardLayout from "@/components/DashboardLayout";
// import axios from 'axios';
//
// interface Article {
//   id: number;
//   title: string;
//   content: string;
//   views: number;
//   ipfsHash: string;
//   createdAt: string;
// }
//
// const Dashboard = () => {
//   const [articles, setArticles] = useState<Article[]>([]);
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState<string | null>(null);
//
//   useEffect(() => {
//     const fetchArticles = async () => {
//       try {
//         // Replace with your actual backend URL
//         const response = await axios.get('http://localhost:8086/store/articles/all', {
//           headers: {
//             // Include authorization token if required
//             'Authorization': `Bearer ${localStorage.getItem('token')}`
//           }
//         });
//
//         // Assuming the response contains the list of articles
//         setArticles(response.data);
//         setLoading(false);
//       } catch (err) {
//         console.error('Error fetching articles:', err);
//         setError('Failed to fetch articles');
//         setLoading(false);
//       }
//     };
//
//     fetchArticles();
//   }, []);
//
//   const handleViewArticle = (ipfsHash: string) => {
//     // Open IPFS gateway link to view article
//     window.open(`http://localhost:8888/ipfs/${ipfsHash}`, '_blank');
//   };
//
//   if (loading) {
//     return (
//         <DashboardLayout>
//           <div className="flex justify-center items-center h-full">
//             Loading articles...
//           </div>
//         </DashboardLayout>
//     );
//   }
//
//   if (error) {
//     return (
//         <DashboardLayout>
//           <div className="text-red-500 text-center">{error}</div>
//         </DashboardLayout>
//     );
//   }
//
//   return (
//       <DashboardLayout>
//         <div className="space-y-8 animate-fade-in">
//           <div className="flex justify-between items-center">
//             <h1 className="text-2xl font-bold">Dashboard</h1>
//             <div className="flex gap-2">
//               {/* Time range buttons remain the same */}
//               <button className="px-4 py-2 rounded-lg hover:bg-gray-100">Yesterday</button>
//               <button className="px-4 py-2 rounded-lg hover:bg-gray-100">Today</button>
//               <button className="px-4 py-2 rounded-lg bg-primary text-white">Last 7 days</button>
//               {/* Other time range buttons */}
//             </div>
//           </div>
//
//           <Card className="p-6">
//             <h2 className="text-xl font-semibold mb-4">Top Performed Articles</h2>
//             <div className="overflow-x-auto">
//               <table className="w-full">
//                 <thead>
//                 <tr className="border-b">
//                   <th className="text-left py-2">Article Name</th>
//                   <th className="text-left py-2">Created At</th>
//                   <th className="text-left py-2">Action</th>
//                 </tr>
//                 </thead>
//                 <tbody>
//                 {articles.map((article) => (
//                     <tr key={article.id} className="border-b">
//                       <td className="py-2">{article.title}</td>
//                       <td className="py-2">
//                         {new Date(article.createdAt).toLocaleDateString()}
//                       </td>
//                       <td className="py-2">
//                         <button
//                             onClick={() => handleViewArticle(article.ipfsHash)}
//                             className="text-primary hover:text-primary-hover"
//                         >
//                           View on IPFS
//                         </button>
//                       </td>
//                     </tr>
//                 ))}
//                 </tbody>
//               </table>
//             </div>
//           </Card>
//
//           {/* World map visualization card remains the same */}
//           <Card className="p-6 h-96 bg-accent">
//             <div className="flex justify-between items-center mb-4">
//               <h2 className="text-xl font-semibold">Global Article Views</h2>
//               {/* Expand button */}
//             </div>
//             <div className="h-full bg-gray-100 rounded-lg flex items-center justify-center">
//               World Map Visualization Placeholder
//             </div>
//           </Card>
//         </div>
//       </DashboardLayout>
//   );
// };
//
// export default Dashboard;